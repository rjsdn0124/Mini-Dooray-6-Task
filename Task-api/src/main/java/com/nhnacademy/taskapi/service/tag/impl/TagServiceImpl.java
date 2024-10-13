package com.nhnacademy.taskapi.service.tag.impl;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.tag.Tag;
import com.nhnacademy.taskapi.entity.tag.dto.TagDto;
import com.nhnacademy.taskapi.entity.tag.request.TagRequest;
import com.nhnacademy.taskapi.error.project.ProjectNotFoundException;
import com.nhnacademy.taskapi.error.projectmember.ProjectMemberUserNotFoundException;
import com.nhnacademy.taskapi.error.tag.TagAlreadyExistsException;
import com.nhnacademy.taskapi.error.tag.TagNotFoundException;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.taskapi.repository.tag.TagRepository;
import com.nhnacademy.taskapi.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public List<TagDto> getAllTags() {

        List<Tag> tagList = tagRepository.findAll();

        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag tag : tagList) {

            tagDtoList.add(new TagDto(tag.getContent()));
        }

        return tagDtoList;
    }

    @Override
    public List<TagDto> getAllTagsByProjectId(Long projectId) {
        if (Objects.isNull(projectId) || projectId < 0) {
            throw new IllegalArgumentException("태그 - 프로젝트 아이디가 옳바르지 않은 값입니다.");
        }

        List<Tag> tagList = tagRepository.findAllByProjectId(projectId);

        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag tag : tagList)
            tagDtoList.add(new TagDto(tag.getTagId(), tag.getContent()));
        return tagDtoList;
    }

    @Override
    public TagDto getTag(Long tagId) {
        if (Objects.isNull(tagId) || tagId < 0) {
            throw new IllegalArgumentException("태그 아이디 값이 올바르지 않습니다.");
        }
        if (!tagRepository.existsById(tagId)) {
            throw new TagNotFoundException(tagId);
        }

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(TagNotFoundException::new);

        return new TagDto(tag.getContent());
    }

    @Override
    public TagDto create(String content, Long projectId, String userId) {
        if (Objects.isNull(content) || Objects.isNull(projectId) || content.isEmpty() || projectId < 0) {
            throw new IllegalArgumentException("제목 또는 프로젝트 아이디가 올바르지 않은 값입니다.");
        }
        if (!projectMemberRepository.existsByUserId(userId)) {
            throw new ProjectMemberUserNotFoundException("프로젝트 참여중인 멤버가 아닙니다.");
        }
        if (tagRepository.existsTagByContent(content)) {
            throw new TagAlreadyExistsException();
        }


        Project project = projectRepository.findById(projectId)
                                            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Tag tag = tagRepository.save(new Tag(content, project));

        return new TagDto(tag.getContent());
    }

    @Override
    public void update(Long tagId, TagRequest tagRequest, String userId) {
        if (Objects.isNull(tagId) || Objects.isNull(tagRequest) || tagId < 0) {
            throw new IllegalArgumentException("태그 아이디에 올바르지 않은 값이 들어왔습니다.");
        }
        if (!projectMemberRepository.existsByUserId(userId)) {
            throw new ProjectMemberUserNotFoundException("프로젝트 참여중인 멤버가 아닙니다.");
        }
        if (!tagRepository.existsById(tagId)) {
            throw new TagNotFoundException(tagId);
        }


        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));

        tag.setContent(tagRequest.getContent());
        tag.setCreatedAt(LocalDateTime.now());

        tagRepository.save(tag);
    }


    @Override
    public void delete(Long tagId, String userId) {
        if (Objects.isNull(tagId) || tagId < 0) {
            throw new IllegalArgumentException("태그 아이디에 올바르지 않은 값이 들어왔습니다.");
        }
        if (!projectMemberRepository.existsByUserId(userId)) {
            throw new ProjectMemberUserNotFoundException("프로젝트 참여중인 멤버가 아닙니다.");
        }
        if (!tagRepository.existsById(tagId)) {
            throw new TagNotFoundException(tagId);
        }

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));

        tagRepository.delete(tag);
    }
}
