package com.example.notes_api.Services;

import com.example.notes_api.DTO.NotesDTO;
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

    public String postNote(Long userId, NotesDTO notesDTO) throws IllegalArgumentException{
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Notes note = NoteMapper.mapDTOToNote(notesDTO);
        user.getNotes().add(note);
        userRepository.save(user);
        return "Note created";
    }

    public List<NotesDTO> getAllNotes(){
        List<NotesDTO> notes = noteRepository.findAll().stream().map(NoteMapper::mapNoteToDTO).toList();
        return notes;
    }
    public String updateNote(NotesDTO notesDTO){
        Notes note = noteRepository.findById(notesDTO.getId()).get();
        note.setTitle(notesDTO.getTitle());
        note.setDescription(notesDTO.getDescription());
        noteRepository.save(note);
        return "Note updated";
    }
    public String deleteNote(Long id){
        noteRepository.deleteById(id);
        return "Note deleted";
    }

}
