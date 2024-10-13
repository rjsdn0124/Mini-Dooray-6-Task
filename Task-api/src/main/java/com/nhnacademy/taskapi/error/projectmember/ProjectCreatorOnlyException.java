package com.nhnacademy.taskapi.error.projectmember;

import com.nhnacademy.taskapi.error.ForbiddenException;

public class ProjectCreatorOnlyException extends ForbiddenException {

    public ProjectCreatorOnlyException() {
        super("프로젝트 관리자만이 생성할 수 있습니다.");
    }
}
