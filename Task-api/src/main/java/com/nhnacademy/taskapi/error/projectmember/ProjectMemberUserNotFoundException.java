package com.nhnacademy.taskapi.error.projectmember;

import com.nhnacademy.taskapi.error.NotFoundException;

public class ProjectMemberUserNotFoundException extends NotFoundException {

    public ProjectMemberUserNotFoundException() {
        super("해당 유저는 참가중인 프로젝트가 존재하지 않습니다.");
    }

    public ProjectMemberUserNotFoundException(String message) {
        super(message + "유저는 참가중인 프로젝트가 없습니다.");
    }
}
