package com.nhnacademy.taskapi.repository.projectmember;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.projectmember.ProjectMember;
import com.nhnacademy.taskapi.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    @Query("SELECT CASE WHEN COUNT(pm) > 0 THEN TRUE ELSE FALSE END FROM ProjectMember pm WHERE pm.user.userId = :userId")
    boolean existsByUserId(String userId);

    @Query("SELECT pm.project FROM ProjectMember pm WHERE pm.user.userId = :userId")
    List<Project> findProjectParticipationByUserId(String userId);

    @Query("SELECT pm.user FROM ProjectMember pm WHERE pm.project.projectId = :projectId")
    List<User> findUserParticipationByProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProjectMember pm WHERE pm.project.projectId = :projectId")
    void deleteByProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProjectMember pm WHERE pm.user.userId = :userId")
    void deleteByUserId(String userId);
}
