package com.nhnacademy.taskapi.controller.project;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.project.dto.ProjectDto;
import com.nhnacademy.taskapi.entity.project.request.ProjectCreateRequest;
import com.nhnacademy.taskapi.entity.project.request.ProjectRequest;
import com.nhnacademy.taskapi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")        /* 모든 프로젝트 목록 조회 */
    public ResponseEntity<?> getProjects(Pageable pageable,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "size", required = false) Integer size
    ) {
        if (Objects.isNull(page) && Objects.isNull(size)) {
            return ResponseEntity.ok(projectService.getAllProjects());
        } else {
            return ResponseEntity.ok(projectService.getProjectsPage(pageable));
        }
    }

    @PostMapping("/projects")       /* 특정 프로젝트 생성 */
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectCreateRequest projectCreateRequest,
                                                 @RequestHeader("X-USER-ID") String userId) {
        ProjectDto project = projectService.create(projectCreateRequest.getTitle(), userId);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/projects/{projectId}") /* 특정 프로젝트 조회 */
    public ResponseEntity<ProjectDto> getProject(@PathVariable("projectId") Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/projects/user") /* 특정 유저가 만든 프로젝트 목록 조회 */
    public ResponseEntity<List<ProjectDto>> getProjectsByUserId(@RequestHeader("X-USER-ID") String userId) {
        return ResponseEntity.ok(projectService.getProjectsByUserId(userId));
    }

    @DeleteMapping("/projects/{projectId}") /* 프로젝트 삭제 */
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") Long projectId,
                                           @RequestHeader("X-USER-ID") String userId) {
        projectService.delete(projectId, userId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/projects/{projectId}")    /* 프로젝트 업데이트 */
    public ResponseEntity<?> updateProject(@PathVariable("projectId") Long projectId,
                                           @RequestHeader("X-USER-ID") String userId,
                                           @RequestBody ProjectRequest projectRequest) {
        projectService.update(projectId, projectRequest.getTitle(), projectRequest.getStatus(), userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
