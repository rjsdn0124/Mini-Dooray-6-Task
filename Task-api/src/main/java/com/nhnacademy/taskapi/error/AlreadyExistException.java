package com.nhnacademy.taskapi.error;

public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() {}

    public AlreadyExistException(String message) {
        super(message);
    }
}
