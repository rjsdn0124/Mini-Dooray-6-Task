package com.nhnacademy.taskapi.controller.project;

import com.nhnacademy.taskapi.entity.project.dto.ProjectDto;
import com.nhnacademy.taskapi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ProjectStatusController {

    private final ProjectService projectService;

    @GetMapping("/projects/active")     /* 활성중인 모든 프로젝트 가져오기 */
    public ResponseEntity<List<ProjectDto>> getAllActiveProjects() {
        return ResponseEntity.ok().body(projectService.getAllActiveProjects());
    }

    @GetMapping("/projects/inactive")      /* 휴면 상태인 모든 프로젝트 가져오기 */
    public ResponseEntity<List<ProjectDto>> getAllInactiveProjects() {
        return ResponseEntity.ok().body(projectService.getAllInactiveProjects());
    }

    @GetMapping("/projects/terminated")     /* 종료 상태인 모든 프로젝트 가져오기 */
    public ResponseEntity<List<ProjectDto>> getAllTerminatedProjects() {
        return ResponseEntity.ok().body(projectService.getAllTerminatedProjects());
    }

    @GetMapping("/projects/active/user")       /* 특정 유저가 만든 모든 활성 프로젝트 가져오기 */
    public ResponseEntity<List<ProjectDto>> getAllActiveProjectsByUserId(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok().body(projectService.getAllActiveProjectsByUser(userId));
    }

    @GetMapping("/projects/inactive/user")       /* 특정 유저가 만든 모든 휴면 상태인 프로젝트 가져오기 */
    public ResponseEntity<List<ProjectDto>> getAllInactiveProjectsByUserId(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok().body(projectService.getAllInactiveProjectsByUser(userId));
    }

    @GetMapping("/projects/terminated/user")       /* 특정 유저가 만든 모든 종료 상태인 프로젝트 가져오기 */
    public ResponseEntity<List<ProjectDto>> getAllTerminatedProjectsByUserId(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok().body(projectService.getAllTerminatedProjectsByUser(userId));
    }
}
