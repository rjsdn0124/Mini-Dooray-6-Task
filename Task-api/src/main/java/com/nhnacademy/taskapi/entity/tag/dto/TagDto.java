package com.nhnacademy.taskapi.entity.tag.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {

    private long tagId;

    private String content;

    public TagDto(long tagId, String content) {
        this.tagId = tagId;
        this.content = content;

    }

    public TagDto(String content) {
        this.content = content;
    }
}
