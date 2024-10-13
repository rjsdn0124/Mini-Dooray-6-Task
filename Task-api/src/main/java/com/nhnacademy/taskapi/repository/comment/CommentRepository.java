package com.nhnacademy.taskapi.repository.comment;

import com.nhnacademy.taskapi.entity.comment.Comment;
import com.nhnacademy.taskapi.entity.comment.dto.CommentDto;
import com.nhnacademy.taskapi.entity.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {


    @Query("SELECT new com.nhnacademy.taskapi.entity.comment.dto.CommentDto(c.commentId, c.content, c.createdAt) " +
            "FROM Comment c WHERE c.task.taskId = :taskId")
    List<CommentDto> findCommentsByTaskId(Long taskId);
}
