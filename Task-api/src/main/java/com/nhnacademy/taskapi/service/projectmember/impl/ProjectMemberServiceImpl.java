package com.nhnacademy.taskapi.service.projectmember.impl;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.project.dto.ProjectDto;
import com.nhnacademy.taskapi.entity.projectmember.ProjectMember;
import com.nhnacademy.taskapi.entity.projectmember.dto.ProjectMemberDto;
import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.error.project.ProjectNotFoundException;
import com.nhnacademy.taskapi.error.projectmember.ProjectCreatorOnlyException;
import com.nhnacademy.taskapi.error.projectmember.ProjectMemberAlreadyExistException;
import com.nhnacademy.taskapi.error.projectmember.ProjectMemberUserNotFoundException;
import com.nhnacademy.taskapi.error.user.UserNotFoundException;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.taskapi.repository.user.UserRepository;
import com.nhnacademy.taskapi.service.projectmember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectMemberDto addMemberToProject(String ownerId, Long projectId, String userId) {
        if (Objects.isNull(userId) || Objects.isNull(projectId) || Objects.isNull(ownerId) || ownerId.isEmpty() || userId.isEmpty() || projectId < 0) {
            throw new IllegalArgumentException("프로젝트멤버 - 들어온 데이터 값이 올바르지 않습니다.");
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        // 프로젝트 생성자인지 체크
        if (!project.getUser().getUserId().equalsIgnoreCase(ownerId)) {
            throw new ProjectCreatorOnlyException();
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 이미 존재하는 유저인지 확인
        List<User> userList = getMembersByProjectId(projectId);
        for (User u : userList) {
            if (u.getUserId().equalsIgnoreCase(user.getUserId())) {
                throw new ProjectMemberAlreadyExistException(userId);
            }
        }

        ProjectMember projectMember = projectMemberRepository.save(new ProjectMember(project, user));

        return new ProjectMemberDto(projectMember.getProject().getProjectId(), projectMember.getUser().getUserId());
    }

    @Override
    public List<ProjectDto> getProjectsByUserId(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디 값이 올바르지 않습니다.");
        }
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
//        if (!projectMemberRepository.existsByUserId(userId)) {
//            throw new ProjectMemberUserNotFoundException(userId);
//        }

        List<Project> projectList = projectMemberRepository.findProjectParticipationByUserId(userId);

        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project : projectList) {
            projectDtoList.add(
                    new ProjectDto(
                            project.getProjectId(),
                            project.getTitle(),
                            project.getStatus()
                    )
            );
        }

        return projectDtoList;
    }

    @Override
    public List<User> getMembersByProjectId(Long projectId) {
        if (Objects.isNull(projectId) || projectId < 0) {
            throw new IllegalArgumentException("프로젝트 아이디 값이 올바르지 않습니다.");
        }
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException();
        }

        return projectMemberRepository.findUserParticipationByProjectId(projectId);
    }
}
