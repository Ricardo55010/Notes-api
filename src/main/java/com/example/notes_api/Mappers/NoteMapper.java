package com.example.notes_api.Mappers;

import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Models.Notes;

public class NoteMapper {
    public static NotesDTO mapNoteToDTO(Notes note){
        return new NotesDTO(note.getId(), note.getTitle(), note.getDescription(), note.getCreatedAt(),note.getUpdatedAt());
    }

    public static Notes mapDTOToNote(NotesDTO noteDTO){
        return new Notes(noteDTO.getTitle(), noteDTO.getDescription(), noteDTO.getCreatedAt(), noteDTO.getUpdatedAt());
    }

}
