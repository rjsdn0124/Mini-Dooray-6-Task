package com.nhnacademy.taskapi.repository.milestone;

import com.nhnacademy.taskapi.entity.milestone.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {

    // 1. 마일스톤 목록 가져오기

    // 2. 마일스톤 추가
    boolean existsByTitle(String title);

    // 3. 마일스톤 삭제

    // 4. 마일스톤 업데이트

    // 5. 특정 마일스톤 가져오기

    // 6. 프로젝트 내 마일스톤 가져오기
    @Query("SELECT m FROM MileStone m WHERE m.project.projectId = :projectId")
    List<MileStone> findAllByProjectId(Long projectId);
}
