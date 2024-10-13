package com.nhnacademy.taskapi.dto.task;

import com.nhnacademy.taskapi.dto.CommentDto;
import com.nhnacademy.taskapi.dto.TagDto;
import com.nhnacademy.taskapi.entity.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailResponseDto {

    private long taskId;

    private String title;

    private String content;

    private ZonedDateTime createdAt;

    @Setter
    private long projectId;

    @Setter
    private List<CommentDto> commentList;

    @Setter
    private List<TagDto> tagList;


    @Setter
    private String milestoneId;

    public TaskDetailResponseDto(Task task) {
        this.taskId = task.getTaskId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.createdAt = task.getCreatedAt();
    }




}
