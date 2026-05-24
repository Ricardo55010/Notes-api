package com.example.notes_api.Exceptions;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(String message){
        super(message);
    }
}
