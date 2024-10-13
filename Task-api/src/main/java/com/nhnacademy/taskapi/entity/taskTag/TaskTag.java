package com.nhnacademy.taskapi.entity.taskTag;


import com.nhnacademy.taskapi.entity.tag.Tag;
import com.nhnacademy.taskapi.entity.task.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@NoArgsConstructor
public class TaskTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskTagId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", nullable = false)  // 외래키 지정
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", nullable = false)  // Tag 외래 키 지정
    private Tag tag;

    public TaskTag(Task task, Tag tag) {
        this.task = task;
        this.tag = tag;
    }

}
