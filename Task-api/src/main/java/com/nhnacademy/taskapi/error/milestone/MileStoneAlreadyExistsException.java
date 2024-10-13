package com.nhnacademy.taskapi.error.milestone;

import com.nhnacademy.taskapi.error.AlreadyExistException;

public class MileStoneAlreadyExistsException extends AlreadyExistException {

    public MileStoneAlreadyExistsException() {
        super("이미 존재하는 마일스톤입니다.");
    }
}
