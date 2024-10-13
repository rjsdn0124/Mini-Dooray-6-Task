package com.nhnacademy.taskapi.dto.task;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.task.Task;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RequestTaskCommand {

    @Length(max = 100)
    @NotEmpty(message = "Title is required.")
    private String title;

    @NotEmpty(message = "Content is required.")
    private String content;

    private long projectId;

    private long milestoneId;

    public Task toEntity(Project project) {
        return new Task(title, content, project, milestoneId);
    }

}
