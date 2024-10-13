package com.nhnacademy.taskapi.entity.tag.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequest {


    private String content;
    private long projectId;

    public TagRequest() {

    }

    public TagRequest(String content, long projectId) {
        this.content = content;
        this.projectId = projectId;
    }
}
