package com.nhnacademy.taskapi.controller.projectmember;


import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.project.dto.ProjectDto;
import com.nhnacademy.taskapi.entity.projectmember.dto.ProjectMemberDto;
import com.nhnacademy.taskapi.entity.projectmember.request.ProjectMemberRequest;
import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.service.projectmember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @PostMapping("/projects/users")
    public ResponseEntity<ProjectMemberDto> addProjectMember(@RequestBody ProjectMemberRequest projectMemberRequest,
                                                             @RequestHeader("X-USER-ID") String userId) {
        ProjectMemberDto projectMemberDto = projectMemberService.addMemberToProject(
                userId,
                projectMemberRequest.getProjectId(),
                projectMemberRequest.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberDto);
    }

    @GetMapping("/users/{userId}/projects")
    public ResponseEntity<List<ProjectDto>> getProjectsByUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(projectMemberService.getProjectsByUserId(userId));
    }

    @GetMapping("/projects/{projectId}/users")
    public ResponseEntity<List<User>> getUsersByProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectMemberService.getMembersByProjectId(projectId));
    }
}
