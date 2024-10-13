package com.nhnacademy.taskapi.service.task.impl;

import com.nhnacademy.taskapi.entity.comment.dto.CommentDto;
import com.nhnacademy.taskapi.entity.milestone.MileStone;
import com.nhnacademy.taskapi.entity.tag.Tag;
import com.nhnacademy.taskapi.entity.tag.dto.TagDto;
import com.nhnacademy.taskapi.entity.task.dto.RequestTaskCommand;
import com.nhnacademy.taskapi.entity.task.dto.TaskDetailResponseDto;
import com.nhnacademy.taskapi.entity.task.dto.TaskSimpleResponseDto;
import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.task.Task;
import com.nhnacademy.taskapi.entity.taskTag.TaskTag;
import com.nhnacademy.taskapi.repository.TaskTag.TaskTagRepository;
import com.nhnacademy.taskapi.repository.comment.CommentRepository;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.tag.TagRepository;
import com.nhnacademy.taskapi.repository.task.TaskRepository;
import com.nhnacademy.taskapi.service.task.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;

    private final CommentRepository commentRepository;

    @Override
    public Page<TaskSimpleResponseDto> getTasksByProjectId(Long projectId, Pageable pageable) {

        return taskRepository.findByProjectId(projectId, pageable);
    }

    @Override
    public TaskDetailResponseDto getTaskById(long taskId) {

        Task task = taskRepository.findByTaskId(taskId);
        List<CommentDto> commentDtoList = commentRepository.findCommentsByTaskId(taskId);

        List<TagDto> tagDtoList = new ArrayList<>();

        for (TaskTag taskTag : task.getTaskTags()) {
            long tagId = taskTag.getTag().getTagId();
            tagDtoList.add(tagRepository.findById(tagId).toDto());
        }

        TaskDetailResponseDto taskDto = new TaskDetailResponseDto(task);

        taskDto.setTagList(tagDtoList);
        taskDto.setCommentList(commentDtoList);

        return taskDto;
    }

    @Transactional
    @Override
    public Task createTask(RequestTaskCommand requestTaskCommand) {
        Project project = projectRepository.findById(requestTaskCommand.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));
        Task task = requestTaskCommand.toEntity(project);

        Task savedTask = taskRepository.save(task);
        taskRepository.flush();
        List<Tag> tagList = requestTaskCommand.getTagList();
        for (Tag tag : tagList) {
            taskTagRepository.save(new TaskTag(task, tag));
        }


        return savedTask;
    }

    @Override
    public boolean deleteTask(Long taskId) {

        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public TaskDetailResponseDto updateTask(long taskId, RequestTaskCommand updateTaskCommand) {
        Project project = projectRepository.findById(updateTaskCommand.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));

        int taskTagIsDeleted = taskTagRepository.deleteByTaskId(taskId);

        List<CommentDto> commentDtoList = commentRepository.findCommentsByTaskId(taskId);

        task.getTaskTags().clear();

        List<Tag> tagList = updateTaskCommand.getTagList();

        if(tagList != null){
            for (Tag tag : tagList) {
                taskTagRepository.save(new TaskTag(task, tag));
            }
            taskTagRepository.flush();
        }

        taskRepository.flush();

        List<TagDto> tagDtoList = new ArrayList<>();
        List<TaskTag> taskTagList = taskTagRepository.findAllByTaskId(taskId);

        for (TaskTag taskTag : taskTagList) {
            long tagId = taskTag.getTag().getTagId();
            tagDtoList.add(tagRepository.findById(tagId).toDto());
        }


        task.setTitle(updateTaskCommand.getTitle());
        task.setContent(updateTaskCommand.getContent());
        task.setMilestoneId(updateTaskCommand.getMilestoneId());


        TaskDetailResponseDto taskDetailResponseDto = new TaskDetailResponseDto(task);
        taskDetailResponseDto.setCommentList(commentDtoList);
        taskDetailResponseDto.setTagList(tagDtoList);

        return taskDetailResponseDto;
    }
}
