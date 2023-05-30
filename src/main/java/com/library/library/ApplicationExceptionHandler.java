package com.library.library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.library.library.expetions.BookNotFoundException;
import com.library.library.responses.MessageResponse;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> bookNotFoundExceptionHandler(BookNotFoundException ex) {
        MessageResponse message = new MessageResponse(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
