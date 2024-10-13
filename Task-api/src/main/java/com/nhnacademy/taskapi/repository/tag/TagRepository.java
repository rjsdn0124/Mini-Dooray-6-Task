package com.nhnacademy.taskapi.repository.tag;

import com.nhnacademy.taskapi.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    // 태그 목록 가져오기

    // 프로젝트 내 태그 목록 가져오기
    @Query("SELECT t FROM Tag t WHERE t.project.projectId = :projectId")
    List<Tag> findAllByProjectId(Long projectId);


    Tag findById(long id);
    // 태그 제목 존재하는 지 확인
    boolean existsTagByContent(String content);

    // 특정 태그 가져오기

    // 태그 생성하기

    // 태그 업데이트

    // 태그 삭제하기
}
