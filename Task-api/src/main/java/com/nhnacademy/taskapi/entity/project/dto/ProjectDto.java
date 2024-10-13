package com.nhnacademy.taskapi.entity.project.dto;

import com.nhnacademy.taskapi.entity.project.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {

    private long projectId;
    private String title;
    private ProjectStatus status;

    public ProjectDto(long projectId, String title, ProjectStatus status) {
        this.projectId = projectId;
        this.title = title;
        this.status = status;
    }
}
