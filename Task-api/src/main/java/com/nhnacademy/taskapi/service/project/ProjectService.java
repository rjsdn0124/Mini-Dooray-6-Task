package com.nhnacademy.taskapi.service.project;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.project.ProjectStatus;
import com.nhnacademy.taskapi.entity.project.dto.ProjectDto;
import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects();

    List<ProjectDto> getAllActiveProjects();

    List<ProjectDto> getAllInactiveProjects();

    List<ProjectDto> getAllTerminatedProjects();

    List<ProjectDto> getAllActiveProjectsByUser(String userId);

    List<ProjectDto> getAllInactiveProjectsByUser(String userId);

    List<ProjectDto> getAllTerminatedProjectsByUser(String userId);

    List<ProjectDto> getProjectsByUserId(String userId);

    Page<ProjectDto> getProjectsPage(Pageable pageable);

    ProjectDto getProjectById(Long projectId);

    ProjectDto create(String title, String userId);

    void delete(Long projectId, String userId);

    void update(Long projectId, String title, ProjectStatus status, String userId);

    UserDto getCreateUserByProjectId(Long projectId);
}
