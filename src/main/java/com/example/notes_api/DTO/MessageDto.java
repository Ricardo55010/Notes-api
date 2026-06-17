package com.example.notes_api.DTO;

public class MessageDto {

    private String name;

    public MessageDto() {
    }

    public MessageDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}