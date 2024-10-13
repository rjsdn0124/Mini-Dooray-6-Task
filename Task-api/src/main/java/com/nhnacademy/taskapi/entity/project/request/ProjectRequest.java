package com.nhnacademy.taskapi.entity.project.request;

import com.nhnacademy.taskapi.entity.project.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

    private String title;
    private ProjectStatus status;

    public ProjectRequest(String title, Integer status) {
        this.title = title;
        this.status = ProjectStatus.fromValue(status);
    }
}
