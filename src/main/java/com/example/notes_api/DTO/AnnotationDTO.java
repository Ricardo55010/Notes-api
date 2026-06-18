package com.example.notes_api.DTO;

import com.example.notes_api.Models.Classification;

public class AnnotationDTO {
    private Long id;
    private String content;

    public AnnotationDTO() {

    }

    public AnnotationDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
