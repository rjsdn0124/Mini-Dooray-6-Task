package com.nhnacademy.taskapi.entity.task;

import com.nhnacademy.taskapi.entity.comment.Comment;
import com.nhnacademy.taskapi.entity.taskTag.TaskTag;
import com.nhnacademy.taskapi.entity.project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;

    @Setter
    @Column(nullable = false)
    private String title;
    @Setter
    private String content;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Setter
    private long milestoneId;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)  // 외래키 지정
    private Project project;

    @Setter
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    @Setter
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskTag> taskTags;

    public Task(long taskId, String title, String content, ZonedDateTime createdAt, Project projet) {
        this.taskId = taskId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.project = projet;
    }

    public Task(String title, String content, Project project, long milestoneId) {
        this.title = title;
        this.content = content;
        this.createdAt = ZonedDateTime.now();
        this.project = project;
        this.milestoneId = milestoneId;
    }
}
