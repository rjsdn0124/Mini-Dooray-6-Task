package com.nhnacademy.taskapi.service.task;

import com.nhnacademy.taskapi.entity.task.dto.RequestTaskCommand;
import com.nhnacademy.taskapi.entity.task.dto.TaskDetailResponseDto;
import com.nhnacademy.taskapi.entity.task.dto.TaskSimpleResponseDto;
import com.nhnacademy.taskapi.entity.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Page<TaskSimpleResponseDto> getTasksByProjectId(Long projectId, Pageable pageable);
    TaskDetailResponseDto getTaskById(long taskId);
    Task createTask(RequestTaskCommand requestTaskCommand);
    boolean deleteTask(Long taskId);
    TaskDetailResponseDto updateTask(long taskId, RequestTaskCommand updateTaskCommand);
}
