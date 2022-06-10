package com.cognato.cmms.exception;

public class UserNotExistsException extends RuntimeException{

    public UserNotExistsException(String message) {
        super(message);
    }
}
