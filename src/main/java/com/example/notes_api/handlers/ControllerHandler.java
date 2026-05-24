package com.example.notes_api.handlers;

import com.example.notes_api.Exceptions.NoteNotFoundException;
import com.example.notes_api.Exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerHandler {
    Logger logger = LoggerFactory.getLogger(ControllerHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception ex){
        logger.error("Exception: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        logger.error("Exception: {}", ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException ex){
        logger.error("Exception: {}", ex.getMessage());
        return ResponseEntity.notFound().build();
    }

}
