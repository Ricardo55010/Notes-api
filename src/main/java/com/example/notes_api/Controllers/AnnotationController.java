package com.example.notes_api.Controllers;

import com.example.notes_api.DTO.AnnotationDTO;
import com.example.notes_api.Services.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/annotations")
public class AnnotationController {
    private AnnotationService annotationService;

    public AnnotationController(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    @PostMapping
    public ResponseEntity<String> addAnnotation(@RequestBody AnnotationDTO annotationDTO, @RequestBody Long noteId) {
        annotationService.addAnnotation(annotationDTO, noteId);
        return ResponseEntity.ok("Annotation added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnnotation(@PathVariable Long id) {
        annotationService.deleteAnnotation(id);
        return ResponseEntity.ok("Annotation deleted");
    }

    @PatchMapping
    public ResponseEntity<String> updateAnnotation(@RequestBody AnnotationDTO annotationDTO) {
        annotationService.updateAnnotation(annotationDTO);
        return ResponseEntity.ok("Annotation updated");
    }

}
