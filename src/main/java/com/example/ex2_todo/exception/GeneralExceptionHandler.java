package com.example.ex2_todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GeneralExceptionHandler {

    private ResponseEntity<HashMap<String, String>> getHashMapResponseEntity(Exception ex, HttpStatus status) {
        HashMap<String, String> error = new HashMap<>();
        error.put("status", status.value() + "");
        error.put("details", status.getReasonPhrase());
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({UsernameNotFoundException.class, NotFoundException.class})
    public ResponseEntity<HashMap<String, String>> getNotFoundException(Exception ex) {
        return getHashMapResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HashMap<String, String>> getBadCredentialsException(BadCredentialsException ex) {
        return getHashMapResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

}
