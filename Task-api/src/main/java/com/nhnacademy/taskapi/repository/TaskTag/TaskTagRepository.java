package com.nhnacademy.taskapi.repository.TaskTag;

import com.nhnacademy.taskapi.entity.tag.Tag;
import com.nhnacademy.taskapi.entity.taskTag.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {


    @Query("SELECT t.project.projectId FROM Task t WHERE t.taskId = :taskId")
    long findProjectIdByTaskId(@Param("taskId") long taskId);

    @Modifying
    @Query("DELETE FROM TaskTag tt WHERE tt.task.taskId = :taskId")
    int deleteByTaskId(long taskId);

    @Query("SELECT tt FROM TaskTag tt WHERE tt.task.taskId = :taskId")
    List<TaskTag> findAllByTaskId(long taskId);
}
