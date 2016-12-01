package com.rasulov.exception;

public class UserDeleteException extends RuntimeException{
    public UserDeleteException() {
    }

    public UserDeleteException(String message) {
        super(message);
    }
}
