package com.nhnacademy.taskapi.entity.task.dto;

import com.nhnacademy.taskapi.entity.comment.dto.CommentDto;
import com.nhnacademy.taskapi.entity.tag.Tag;
import com.nhnacademy.taskapi.entity.tag.dto.TagDto;
import com.nhnacademy.taskapi.entity.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
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
    private long milestoneId;

    public TaskDetailResponseDto(Task task) {
        this.taskId = task.getTaskId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.createdAt = task.getCreatedAt();
        this.milestoneId = task.getMilestoneId();
        this.projectId = task.getProject().getProjectId();

        List<TagDto> tagDtoList = new ArrayList<>();

        task.getTaskTags().forEach(taskTag -> {
            Tag tag = taskTag.getTag();
            TagDto tagDto = new TagDto(tag.getTagId(), tag.getContent());
            tagDtoList.add(tagDto);
        });
        this.tagList = tagDtoList;
    }




}
