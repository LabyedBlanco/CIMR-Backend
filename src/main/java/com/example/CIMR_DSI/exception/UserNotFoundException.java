package com.example.CIMR_DSI.exception;

public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
}
