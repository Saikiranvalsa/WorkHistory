package com.saikiran.WorkHistory.exception;

public class UserAlreadyFound extends RuntimeException {
    public UserAlreadyFound(String message){
        super(message);
    }
}
