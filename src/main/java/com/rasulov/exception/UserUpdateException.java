package com.rasulov.exception;


public class UserUpdateException extends RuntimeException{
    public UserUpdateException() {
    }

    public UserUpdateException(String message) {
        super(message);
    }
}
