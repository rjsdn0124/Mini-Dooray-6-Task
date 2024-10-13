package com.nhnacademy.taskapi.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class TaskSimpleResponseDto {
    private long taskId;
    private String title;
    private String content;
    private ZonedDateTime createdAt;

    private long projectId;
}
