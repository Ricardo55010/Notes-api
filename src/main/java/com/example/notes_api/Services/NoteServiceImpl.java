package com.example.notes_api.Services;

import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Exceptions.NoteNotFoundException;
import com.example.notes_api.Exceptions.UserNotFoundException;
import com.example.notes_api.Mappers.NoteMapper;
import com.example.notes_api.Models.Classification;
import com.example.notes_api.Models.Notes;
import com.example.notes_api.Models.User;
import com.example.notes_api.Models.Workplace;
import com.example.notes_api.Repositories.NoteRepository;
import com.example.notes_api.Repositories.UserRepository;
import com.example.notes_api.Repositories.WorkPlaceRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("noteServiceImpl")
public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;
    UserRepository userRepository;
    WorkPlaceRepository workPlaceRepository;
    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, WorkPlaceRepository workPlaceRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.workPlaceRepository = workPlaceRepository;
    }

    public Long postNote(Long userId, NotesDTO notesDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Notes note = NoteMapper.mapDTOToNote(notesDTO);
        user.getNotes().add(note);
        userRepository.save(user);
        return note.getId();
    }

    public List<NotesDTO> getAllNotes(){
        List<NotesDTO> notes = noteRepository.findAll().stream().map(NoteMapper::mapNoteToDTO).filter(notesDTO -> notesDTO.getClassification() == Classification.PUBLIC).toList();
        return notes;
    }
    public String updateNote(NotesDTO notesDTO){
        Notes note = noteRepository.findById(notesDTO.getId()).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        validateUserForNoteAuthorization(note);

        note.setTitle(notesDTO.getTitle());
        note.setDescription(notesDTO.getDescription());
        noteRepository.save(note);
        return "Note updated";
    }
    public String deleteNote(Long id){
        Notes note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        validateUserForNoteAuthorization(note);
        noteRepository.deleteById(id);
        return "Note deleted";
    }

    public NotesDTO getNoteById(Long id){
        Notes note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        validateUserForNoteAuthorization(note);
        return NoteMapper.mapNoteToDTO(note);
    }

    public void validateUserForNoteAuthorization(Notes note){
        var principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByName(principal.getName());
        List<Workplace> workplace = workPlaceRepository.findAll().stream().filter(workplace1 ->
                workplace1.getNotes().contains(note) && workplace1.getUsers().contains(user)
        ).toList();
        if((!user.getNotes().contains(note) && workplace.isEmpty()  ) &&  !user.getRole().equals("ADMIN") ){
            throw new RuntimeException("User does not have authorization to delete note");
        }
    }
}
