package com.nhnacademy.taskapi.error.project;

import com.nhnacademy.taskapi.error.ForbiddenException;

public class ProjectNotCreatorException extends ForbiddenException {

    public ProjectNotCreatorException() {
        super("프로젝트 관리자가 아닙니다.");
    }

    public ProjectNotCreatorException(String message) {
        super(message + "의 회원은 대상 프로젝트의 관리자가 아닙니다.");
    }
}
