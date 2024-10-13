package com.nhnacademy.taskapi.controller.task;

import com.nhnacademy.taskapi.entity.task.dto.RequestTaskCommand;
import com.nhnacademy.taskapi.entity.task.dto.TaskDetailResponseDto;
import com.nhnacademy.taskapi.entity.task.dto.TaskSimpleResponseDto;
import com.nhnacademy.taskapi.entity.task.Task;
import com.nhnacademy.taskapi.service.task.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api")
@RestController
@Tag(name = "Task Management", description = "Task 관련 API")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "프로젝트 ID로 Task 조회", description = "프로젝트 ID에 해당하는 Task 목록을 페이지로 조회합니다.")
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<Page<TaskSimpleResponseDto>> getTaskByProjectId(@PathVariable Long projectId, Pageable pageable) {

        Page<TaskSimpleResponseDto> taskPage = taskService.getTasksByProjectId(projectId, pageable);

        if (taskPage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(taskPage);
    }


    @Operation(summary = "Task ID로 Task 조회", description = "Task ID에 해당하는 Task 정보를 조회합니다.")
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDetailResponseDto> getTaskById(@PathVariable long taskId) {

        TaskDetailResponseDto task = taskService.getTaskById(taskId);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(task);
    }


    @Operation(summary = "Task 생성", description = "새로운 Task를 생성합니다.")
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody RequestTaskCommand requestTaskCommand) {

        Task createdTask = taskService.createTask(requestTaskCommand);

        URI location = URI.create("/tasks/" + createdTask.getTaskId());

        return ResponseEntity.created(location).body(createdTask);
    }




    @Operation(summary = "Task 삭제", description = "Task ID에 해당하는 Task를 삭제합니다.")
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean isDeleted = taskService.deleteTask(taskId);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Task 업데이트", description = "Task ID에 해당하는 Task를 업데이트합니다.")
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDetailResponseDto> updateTask(@PathVariable Long taskId, @Valid @RequestBody RequestTaskCommand updateTaskCommand) {
        TaskDetailResponseDto updatedTask = taskService.updateTask(taskId, updateTaskCommand);

        if (updatedTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedTask);
    }


}
