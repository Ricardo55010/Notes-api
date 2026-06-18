package com.example.notes_api.Services;

import com.example.notes_api.DTO.AnnotationDTO;
import com.example.notes_api.DTO.NotesDTO;
import com.example.notes_api.Exceptions.NoteNotFoundException;
import com.example.notes_api.Exceptions.UserNotFoundException;
import com.example.notes_api.Mappers.AnnotationMapper;
import com.example.notes_api.Mappers.NoteMapper;
import com.example.notes_api.Models.Annotation;
import com.example.notes_api.Models.Classification;
import com.example.notes_api.Models.Notes;
import com.example.notes_api.Models.User;
import com.example.notes_api.Repositories.AnnotationRepository;
import com.example.notes_api.Repositories.NoteRepository;
import com.example.notes_api.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnotationServiceImpl implements AnnotationService {

    NoteRepository noteRepository;
    AnnotationRepository annotationRepository;
    public AnnotationServiceImpl(NoteRepository noteRepository, AnnotationRepository annotationRepository){
        this.noteRepository = noteRepository;
        this.annotationRepository = annotationRepository;
    }

    @Override
    public Long addAnnotation(AnnotationDTO annotationDTO, Long noteId) {
        Annotation annotation = AnnotationMapper.mapAnnotationDTOToAnnotation(annotationDTO);
        Notes note = noteRepository.findById(noteId).orElseThrow(()-> new RuntimeException("Note not found"));

        return  annotationRepository.save(annotation).getId();
    }

    @Override
    public Long deleteAnnotation(Long id){
        annotationRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateAnnotation(AnnotationDTO annotationDT){
        Annotation annotation = annotationRepository.findById(annotationDT.getId()).orElseThrow(()-> new RuntimeException("ANnotation not found"));
        annotation.setContent(annotationDT.getContent());
        annotationRepository.save(annotation);
        return annotation.getId();
    }

}
