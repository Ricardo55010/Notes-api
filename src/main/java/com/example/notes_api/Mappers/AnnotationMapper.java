package com.example.notes_api.Mappers;

import com.example.notes_api.DTO.AnnotationDTO;
import com.example.notes_api.Models.Annotation;

public class AnnotationMapper {
    public static AnnotationDTO mapAnnotationToAnnotationDTO(Annotation annotation) {
        AnnotationDTO dto = new AnnotationDTO(annotation.getId(), annotation.getContent());
        return dto;
    }

    public static Annotation mapAnnotationDTOToAnnotation(AnnotationDTO dto) {
        Annotation annotation = new Annotation(dto.getContent());
        return annotation;
    }
}
