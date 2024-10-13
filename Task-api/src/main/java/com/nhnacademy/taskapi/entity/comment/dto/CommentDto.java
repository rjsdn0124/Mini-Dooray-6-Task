package com.nhnacademy.taskapi.entity.comment.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {

    private long commentId;
    private String content;
    private ZonedDateTime createdAt;
}
