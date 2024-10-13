package com.nhnacademy.taskapi.entity.projectmember.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberRequest {

    private String ownerId;

    private Long projectId;

    private String userId;

    public ProjectMemberRequest() {

    }

    public ProjectMemberRequest(String ownerId, Long projectId, String userId) {
        this.ownerId = ownerId;
        this.projectId = projectId;
        this.userId = userId;
    }
}
