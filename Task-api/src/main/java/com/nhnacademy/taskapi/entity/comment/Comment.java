package com.nhnacademy.taskapi.entity.comment;


import com.nhnacademy.taskapi.entity.task.Task;
import com.nhnacademy.taskapi.entity.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    private ZonedDateTime createdAt;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
