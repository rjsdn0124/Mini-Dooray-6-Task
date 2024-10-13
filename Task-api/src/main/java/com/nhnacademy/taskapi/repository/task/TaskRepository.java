package com.nhnacademy.taskapi.repository.task;

import com.nhnacademy.taskapi.entity.task.dto.TaskSimpleResponseDto;
import com.nhnacademy.taskapi.entity.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository  extends JpaRepository<Task, Long> {

    @Query("SELECT new com.nhnacademy.taskapi.entity.task.dto.TaskSimpleResponseDto(t.taskId, t.title, t.content,t.createdAt, t.project.projectId) " +
            "FROM Task t " + "WHERE t.project.projectId = :projectId")
    Page<TaskSimpleResponseDto> findByProjectId(long projectId, Pageable pageable);

    Task findByTaskId(Long taskId);
}
