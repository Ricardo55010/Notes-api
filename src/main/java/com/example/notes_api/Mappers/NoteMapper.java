package com.example.notes_api.Mappers;

import com.example.notes_api.DTO.AnnotationDTO;
import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Models.Annotation;
import com.example.notes_api.Models.Notes;

import java.util.List;

public class NoteMapper {
    public static NotesDTO mapNoteToDTO(Notes note){
        List<AnnotationDTO> annotations = note.getAnnotations().stream().map(AnnotationMapper::mapAnnotationToAnnotationDTO).toList();
        return new NotesDTO(note.getId(), note.getTitle(), note.getDescription(), note.getCreatedAt(),note.getUpdatedAt(), note.getClassification(),annotations);
    }

    public static Notes mapDTOToNote(NotesDTO noteDTO){
        List<Annotation> annotations = noteDTO.getAnnotations().stream().map(AnnotationMapper::mapAnnotationDTOToAnnotation).toList();
        return new Notes(noteDTO.getTitle(), noteDTO.getDescription(), noteDTO.getCreatedAt(), noteDTO.getUpdatedAt(), noteDTO.getClassification(), annotations );
    }

}
