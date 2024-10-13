package com.nhnacademy.taskapi.service.project.impl;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.project.ProjectStatus;
import com.nhnacademy.taskapi.entity.projectmember.ProjectMember;
import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.entity.project.dto.ProjectDto;
import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import com.nhnacademy.taskapi.error.project.ProjectAlreadyExistsException;
import com.nhnacademy.taskapi.error.project.ProjectNotCreatorException;
import com.nhnacademy.taskapi.error.project.ProjectNotFoundException;
import com.nhnacademy.taskapi.error.user.UserNotFoundException;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.taskapi.repository.user.UserRepository;
import com.nhnacademy.taskapi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAllBy();

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projects) {
            projectDtoList.add(
                    new ProjectDto(
                            project.getProjectId(),
                            project.getTitle(),
                            project.getStatus())
            );
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllActiveProjects() {
        List<Project> projectList = projectRepository.getActiveProjects();

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllInactiveProjects() {
        List<Project> projectList = projectRepository.getInactiveProjects();

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllTerminatedProjects() {
        List<Project> projectList = projectRepository.getTerminatedProjects();

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllActiveProjectsByUser(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디가 비어있거나 올바르지 않습니다.");
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<Project> projectList = projectRepository.getActiveProjects(userId);

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllInactiveProjectsByUser(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디가 비어있거나 올바르지 않습니다.");
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<Project> projectList = projectRepository.getInactiveProjects(userId);

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getAllTerminatedProjectsByUser(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디가 비어있거나 올바르지 않습니다.");
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<Project> projectList = projectRepository.getTerminatedProjects(userId);

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public List<ProjectDto> getProjectsByUserId(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디가 비어있거나 Null 입니다.");
        }
        List<Project> projectList = projectRepository.findProjectsByUserId(userId);

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(new ProjectDto(
                    project.getProjectId(),
                    project.getTitle(),
                    project.getStatus()));
        }

        return projectDtoList;
    }

    @Override
    public Page<ProjectDto> getProjectsPage(Pageable pageable) {
        Page<Project> projectPage = projectRepository.findAll(pageable);

        List<ProjectDto> projectDtoList = projectPage.getContent().stream()
                .map(project -> new ProjectDto(
                        project.getProjectId(),
                        project.getTitle(),
                        project.getStatus()))
                .collect(Collectors.toList());

        return new PageImpl<>(projectDtoList, pageable, projectPage.getTotalElements());
    }

    @Override
    public ProjectDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        return new ProjectDto(
                project.getProjectId(),
                project.getTitle(),
                project.getStatus());
    }

    @Override
    public ProjectDto create(String title, String userId) {

        if (Objects.isNull(userId) || Objects.isNull(title)
        || title.isEmpty() || userId.isEmpty()) {
            throw new IllegalArgumentException("옳바르지 않은 값이 들어왔습니다 - 프로젝트 생성");
        }

        if (projectRepository.existsByTitle(title)) { // title 기준으로 변경
            throw new ProjectAlreadyExistsException();
        }

        User addUser = new User(userId);
        Project project = projectRepository.save(new Project(title, ProjectStatus.ACTIVE, addUser));

        projectMemberRepository.save(
                new ProjectMember(project, addUser)
        );

        return new ProjectDto(
                project.getProjectId(),
                project.getTitle(),
                project.getStatus()
        );
    }

    @Override
    public void delete(Long projectId, String userId) {
        if (Objects.isNull(projectId) || projectId < 0 || Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("올바르지 못한 값이 들어왔습니다.");

        }
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);

        projectMemberRepository.deleteByProjectId(project.getProjectId());

        if (!project.getUser().getUserId().equalsIgnoreCase(userId)) {
            throw new ProjectNotCreatorException("프로젝트 생성자가 아닙니다");
        }

        project.setStatus(ProjectStatus.TERMINATED);

        projectRepository.save(project);
    }

    @Override
    public void update(Long projectId, String title, ProjectStatus status, String userId) {
        if (Objects.isNull(projectId) || Objects.isNull(status) || Objects.isNull(title)
                || Objects.isNull(userId) || userId.isEmpty() || title.isEmpty() || projectId < 0) {
            throw new IllegalArgumentException("프로젝트에 필요한 값들이 올바르지 않습니다.");
        }
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException();
        }

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);

        if (project.getStatus().equals(ProjectStatus.TERMINATED)) {
            throw new IllegalArgumentException("종료된 프로젝트 입니다.");
        }

        if (!project.getUser().getUserId().equalsIgnoreCase(userId)) {
            throw new ProjectNotCreatorException("프로젝트 생성자가 아닙니다");
        }

        project.setStatus(status);
        project.setTitle(title);

        projectRepository.save(project);
    }

    @Override
    public UserDto getCreateUserByProjectId(Long projectId) {
        if (Objects.isNull(projectId) || projectId < 0) {
            throw new IllegalArgumentException("프로젝트 아이디 값이 올바르지 않습니다.");
        }

        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException();
        }

        User user = projectRepository.getUserByProjectId(projectId);

        if (Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        return new UserDto(user.getUserId());
    }
}
