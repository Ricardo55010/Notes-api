package com.example.notes_api.Services;

import com.example.notes_api.DTO.NotesDTO;

import java.util.List;

public interface NoteService {
    String postNote(Long userId, NotesDTO notesDTO);
    List<NotesDTO> getAllNotes();
    String updateNote(NotesDTO noteDTO);
    String deleteNote(Long id);
}
