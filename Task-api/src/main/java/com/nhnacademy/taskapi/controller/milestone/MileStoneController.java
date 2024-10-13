package com.nhnacademy.taskapi.controller.milestone;

import com.nhnacademy.taskapi.entity.milestone.dto.MileStoneDto;
import com.nhnacademy.taskapi.entity.milestone.request.MileStoneRequest;
import com.nhnacademy.taskapi.service.milestone.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    @GetMapping("/milestones")      /* 마일스톤 모든 목록 가져오기 */
    public ResponseEntity<List<MileStoneDto>> getAllMileStones() {
        return ResponseEntity.ok(mileStoneService.getAllMileStones());
    }

    @PostMapping("/milestones")         /* 마일스톤 생성하기 */
    public ResponseEntity<MileStoneDto> createMileStone(@RequestBody MileStoneRequest MileStoneRequest,
                                                        @RequestHeader("X-USER-ID") String userId) {
        MileStoneDto mileStone = mileStoneService.create(
                userId,
                MileStoneRequest.getTitle(),
                MileStoneRequest.getInitDate(),
                MileStoneRequest.getDueDate(),
                MileStoneRequest.getProjectId());
        return ResponseEntity.status(HttpStatus.CREATED).body(mileStone);
    }

    @PutMapping("/milestones/{milestoneId}")        /* 마일스톤 업데이트 */
    public ResponseEntity<?> updateMileStone(@PathVariable("milestoneId") Long id,
                                             @RequestBody MileStoneRequest MileStoneRequest,
                                             @RequestHeader("X-USER-ID")String userId) {
        mileStoneService.update(id, MileStoneRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/milestones/{milestoneId}")        /* 특정 마일스톤 가져오기 */
    public ResponseEntity<MileStoneDto> getMileStone(@PathVariable("milestoneId") Long id) {
        return ResponseEntity.ok(mileStoneService.getByMileStoneId(id));
    }

    @DeleteMapping("/milestones/{milestoneId}")         /* 특정 마일스톤 삭제하기 */
    public ResponseEntity<?> deleteMileStone(@PathVariable("milestoneId") Long id,
                                             @RequestHeader("X-USER-ID") String userId) {
        mileStoneService.delete(id, userId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/milestones/projects/{projectId}")         /* 특정 프로젝트의 모든 마일스톤 가져오기 */
    public ResponseEntity<List<MileStoneDto>> getMileStonesByProjectId(@PathVariable("projectId") Long id) {
        return ResponseEntity.ok().body(mileStoneService.getAllMieStonesByProjectId(id));
    }
}
