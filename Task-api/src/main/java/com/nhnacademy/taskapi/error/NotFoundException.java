package com.nhnacademy.taskapi.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {

    }

    public NotFoundException(String message) {
        super(message);
    }
}
