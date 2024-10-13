package com.nhnacademy.taskapi.controller.tag;

import com.nhnacademy.taskapi.entity.tag.dto.TagDto;
import com.nhnacademy.taskapi.entity.tag.request.TagRequest;
import com.nhnacademy.taskapi.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/tags")        /* 모든 태그 가져오기 */
    public ResponseEntity<List<TagDto>> getAllTags() {
        return ResponseEntity.ok().body(tagService.getAllTags());
    }

    @GetMapping("/tags/projects/{projectId}")       /* 특정 프로젝트의 모든 태그 가져오기 */
    public ResponseEntity<List<TagDto>> getAllTagsByProjectId(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(tagService.getAllTagsByProjectId(projectId));
    }

    @GetMapping("/tags/{tagId}")            /* 특정 태그 가져오기 */
    public ResponseEntity<TagDto> getTagById(@PathVariable("tagId") Long tagId) {
        return ResponseEntity.ok().body(tagService.getTag(tagId));
    }

    @PostMapping("/tags")               /* 태그 생성 */
    public ResponseEntity<TagDto> create(@RequestBody TagRequest tagRequest,
                                         @RequestHeader("X-USER-ID") String userId) {
        TagDto tagDto = tagService.create(tagRequest.getContent(), tagRequest.getProjectId(), userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagDto);
    }

    @PutMapping("/tags/{tagId}")            /* 태그 업데이트 */
    public ResponseEntity<?> update(@RequestBody TagRequest tagRequest,
                                    @PathVariable("tagId") Long tagId,
                                    @RequestHeader("X-USER-ID") String userId) {
        tagService.update(tagId, tagRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/tags/{tagId}")         /* 태그 삭제 */
    public ResponseEntity<?> delete(@PathVariable("tagId") Long tagId,
                                    @RequestHeader("X-USER-ID") String userId) {
        tagService.delete(tagId, userId);
        return ResponseEntity.ok().body(null);
    }
}
