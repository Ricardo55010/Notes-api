package com.example.notes_api.DTO;

import com.example.notes_api.Models.Classification;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NotesDTO {
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Classification classification;
    private List<AnnotationDTO> annotations;

    public NotesDTO(){

    }

    public NotesDTO(Long id, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Classification classification,List<AnnotationDTO> annotations) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.classification = classification;
        this.annotations = annotations;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {

    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {

    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {}

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {}
    public Classification getClassification() {
        return classification;
    }
    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    public List<AnnotationDTO> getAnnotations() {
        return annotations;

    }
    public void setAnnotations(List<AnnotationDTO> annotations) {
        this.annotations = annotations;
    }
}
