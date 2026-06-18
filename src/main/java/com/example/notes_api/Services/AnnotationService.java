package com.example.notes_api.Services;

import com.example.notes_api.DTO.AnnotationDTO;
import com.example.notes_api.DTO.NotesDTO;

import java.util.List;

public interface AnnotationService {
    Long addAnnotation(AnnotationDTO annotationDTO, Long noteId);
    Long deleteAnnotation(Long id);
    Long updateAnnotation(AnnotationDTO annotationDT);
}
