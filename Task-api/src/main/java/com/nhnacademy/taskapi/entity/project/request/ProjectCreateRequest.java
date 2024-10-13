package com.nhnacademy.taskapi.entity.project.request;

import com.nhnacademy.taskapi.entity.project.ProjectStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ProjectCreateRequest {
    private String title;
}
