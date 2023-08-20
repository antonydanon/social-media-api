package com.antonydanon.socialmedia.auth.exception;

import com.antonydanon.socialmedia.general.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(CONFLICT).body(new ExceptionResponse(e.getMessage()));
    }
}
