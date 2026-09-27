package com.saikiran.WorkHistory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserAlreadyFound.class)
    public ResponseEntity<String> handleUserFoundException(UserAlreadyFound userAlreadyFound){
        return new ResponseEntity<>(userAlreadyFound.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> hanleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return new ResponseEntity<>(usernameNotFoundException.getMessage(),HttpStatus.CONFLICT);
    }
}
