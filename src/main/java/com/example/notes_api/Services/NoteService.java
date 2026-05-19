package com.example.notes_api.Services;

import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Models.Notes;
import com.example.notes_api.Repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    NoteRepository noteRepository;
    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public void postNote(NotesDTO note){
        noteRepository.save(new Notes(note.getTitle(), note.getDescription(),note.getCreatedAt(),note.getUpdatedAt()));
        return;
    }

    public List<NotesDTO> getALl(){
        List<NotesDTO> notes = noteRepository.findAll().stream().map(note -> new NotesDTO(note.getId(), note.getTitle(), note.getDescription(), note.getCreatedAt(), note.getUpdatedAt())).toList();
        return notes;
    }

}
