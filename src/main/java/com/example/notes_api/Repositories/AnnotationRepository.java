package com.example.notes_api.Repositories;

import com.example.notes_api.Models.Annotation;
import com.example.notes_api.Models.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
}
