package com.nhnacademy.taskapi.entity.projectmember.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberDto {

    private Long projectId;
    private String userId;

    public ProjectMemberDto(Long projectId, String userId) {
        this.projectId = projectId;
        this.userId = userId;
    }
}
