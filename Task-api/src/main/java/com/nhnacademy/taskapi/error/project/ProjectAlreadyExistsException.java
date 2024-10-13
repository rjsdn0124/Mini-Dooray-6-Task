package com.nhnacademy.taskapi.error.project;

import com.nhnacademy.taskapi.error.AlreadyExistException;

public class ProjectAlreadyExistsException extends AlreadyExistException {
    public ProjectAlreadyExistsException() {
        super("해당 프로젝트가 이미 존재합니다.");
    }
}
