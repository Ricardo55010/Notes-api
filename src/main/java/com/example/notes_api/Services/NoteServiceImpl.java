package com.example.notes_api.Services;

import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Exceptions.NoteNotFoundException;
import com.example.notes_api.Exceptions.UserNotFoundException;
import com.example.notes_api.Mappers.NoteMapper;
import com.example.notes_api.Models.Notes;
import com.example.notes_api.Models.User;
import com.example.notes_api.Repositories.NoteRepository;
import com.example.notes_api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("noteServiceImpl")
public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;
    UserRepository userRepository;
    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Long postNote(Long userId, NotesDTO notesDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Notes note = NoteMapper.mapDTOToNote(notesDTO);
        user.getNotes().add(note);
        userRepository.save(user);
        return note.getId();
    }

    public List<NotesDTO> getAllNotes(){
        List<NotesDTO> notes = noteRepository.findAll().stream().map(NoteMapper::mapNoteToDTO).toList();
        return notes;
    }
    public String updateNote(NotesDTO notesDTO){
        Notes note = noteRepository.findById(notesDTO.getId()).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        note.setTitle(notesDTO.getTitle());
        note.setDescription(notesDTO.getDescription());
        noteRepository.save(note);
        return "Note updated";
    }
    public String deleteNote(Long id){
        Notes note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        noteRepository.deleteById(id);
        return "Note deleted";
    }

    public NotesDTO getNoteById(Long id){
        Notes note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        return NoteMapper.mapNoteToDTO(note);
    }

}
