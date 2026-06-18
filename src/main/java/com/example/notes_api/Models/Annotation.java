package com.example.notes_api.Models;

import jakarta.persistence.*;

@Entity
@Table(name="annotation")
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    String content;

    public Annotation() {

    }

    public Annotation(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {}
}
