package com.nhnacademy.taskapi.entity.projectmember;

import com.nhnacademy.taskapi.entity.project.Project;
import com.nhnacademy.taskapi.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProjectMember {

    @Id
    @Column(name = "project_member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectMemberId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ProjectMember(Project project, User user) {
        this.project = project;
        this.user = user;
    }

}
