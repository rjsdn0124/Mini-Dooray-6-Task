package com.nhnacademy.taskapi.service.tag;

import com.nhnacademy.taskapi.entity.tag.dto.TagDto;
import com.nhnacademy.taskapi.entity.tag.request.TagRequest;

import java.util.List;

public interface TagService {

    // 태그 목록 가져오기
    List<TagDto> getAllTags();

    // 프로젝트 내 태그 목록 가져오기
    List<TagDto> getAllTagsByProjectId(Long projectId);

    // 특정 태그 가져오기
    TagDto getTag(Long tagId);

    // 태그 생성하기
    TagDto create(String content, Long projectId, String userId);

    // 태그 업데이트
    void update(Long tagId, TagRequest tagRequest,String userId);

    // 태그 삭제하기
    void delete(Long tagId, String userId);
}
