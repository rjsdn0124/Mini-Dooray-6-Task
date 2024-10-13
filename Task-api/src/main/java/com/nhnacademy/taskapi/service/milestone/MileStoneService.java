package com.nhnacademy.taskapi.service.milestone;

import com.nhnacademy.taskapi.entity.milestone.dto.MileStoneDto;
import com.nhnacademy.taskapi.entity.milestone.request.MileStoneRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MileStoneService {

    // 1. 마일스톤 목록 가져오기
    List<MileStoneDto> getAllMileStones();

    // 2. 마일스톤 추가
    MileStoneDto create(String userId, String title, LocalDate initDate, LocalDate dueDate, Long projectId);

    // 3. 마일스톤 삭제
    void delete(Long mileStoneId, String userId);

    // 4. 마일스톤 업데이트
    MileStoneDto update(Long mileStoneId, MileStoneRequest request, String userId);

    // 5. 특정 마일스톤 가져오기
    MileStoneDto getByMileStoneId(Long mileStoneId);

    // 6. 프로젝트 내 마일스톤 가져오기
    List<MileStoneDto> getAllMieStonesByProjectId(Long projectId);

}
