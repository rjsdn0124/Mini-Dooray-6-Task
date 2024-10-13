package com.nhnacademy.taskapi.error.tag;

import com.nhnacademy.taskapi.error.AlreadyExistException;

public class TagAlreadyExistsException extends AlreadyExistException {

    public TagAlreadyExistsException() {
        super("이미 존재하는 태그입니다.");
    }

    public TagAlreadyExistsException(String message) {
        super(message);
    }
}
