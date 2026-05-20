package com.example.notes_api.Services;

import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Mappers.NoteMapper;
import com.example.notes_api.Models.Notes;
import com.example.notes_api.Repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("noteServiceImpl")
public class NoteServiceImpl implements NoteService {

    NoteRepository noteRepository;
    public NoteServiceImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public String postNote(NotesDTO notesDTO){
        Notes note = NoteMapper.mapDTOToNote(notesDTO);
        noteRepository.save(note);
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
