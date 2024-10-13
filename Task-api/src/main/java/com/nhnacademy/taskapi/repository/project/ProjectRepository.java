package com.nhnacademy.taskapi.repository.project;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // 1. 프로젝트 목록 가져오기
    @Query("SELECT p FROM Project p")
    List<Project> findAllBy();

    // 2. 특정 유저가 생성한 모든 프로젝트 조회
    @Query("SELECT p FROM Project p WHERE p.user.userId = :userId")
    List<Project> findProjectsByUserId(@Param("userId") String userId);

    // 3. 특정 프로젝트 가져오기
    // projectRepository.findById(projectId)

    // 4. save
    // projectRepository.save()

    // 5. 제목 존재 여부 확인
    boolean existsByTitle(String title);

    // 6. 프로젝트 삭제
    boolean deleteByProjectId(Long projectId);

    // 7. 프로젝트 업데이트
    // update

    // 8. 활성중인 프로젝트만 가져오기
    @Query("SELECT p FROM Project p WHERE p.status = 1")
    List<Project> getActiveProjects();

    // 9. 휴면인 프로젝트만 가져오기
    @Query("SELECT p FROM Project p WHERE p.status = 2")
    List<Project> getInactiveProjects();

    // 10. 종료인 프로젝트만 가져오기
    @Query("SELECT p FROM Project p WHERE p.status = 3")
    List<Project> getTerminatedProjects();

    // 11. 특정 유저가 만든 활성중인 프로젝트만 가져오기
    @Query("SELECT p FROM Project p WHERE p.status = 1 AND p.user.userId = :userId")
    List<Project> getActiveProjects(String userId);

    // 12. 특정 유저가 만든 휴면인 프로젝트만 가져오기
    @Query("SELECT p FROM Project p WHERE p.status = 2 AND p.user.userId = :userId")
    List<Project> getInactiveProjects(String userId);

    // 13. 특정 유저가 만든 종료인 프로젝트만 가져오기
    @Query("SELECT p FROM Project p WHERE p.status = 3 AND p.user.userId = :userId")
    List<Project> getTerminatedProjects(String userId);

    // 14. 특정 프로젝트의 생성자 가져오기
    @Query("SELECT p.user FROM Project p WHERE p.projectId = :projectId")
    User getUserByProjectId(Long projectId);

    // 15. 프로젝트 만든 유저가 삭제되면 삭제하기
    @Modifying
    @Transactional
    @Query("DELETE FROM Project p WHERE p.user.userId = :userId")
    void deleteByUserId(String userId);
}
