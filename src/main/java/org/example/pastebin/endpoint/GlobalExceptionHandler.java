package org.example.pastebin.endpoint;

import org.example.pastebin.exception.TextBlockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TextBlockNotFoundException.class)
    public ResponseEntity<String> handleTextBlockNotFoundException(TextBlockNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Text block not found");
    }

}
