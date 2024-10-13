package com.nhnacademy.taskapi.entity.project;

import com.nhnacademy.taskapi.converter.ProjectStatusConverter;
import com.nhnacademy.taskapi.entity.projectmember.ProjectMember;
import com.nhnacademy.taskapi.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @Setter
    @Length(max = 50)
    @Column(nullable = false)
    private String title;

    @Setter
    @Convert(converter = ProjectStatusConverter.class)
    @Column(nullable = false)
    private ProjectStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<ProjectMember> projectMembers;

    public Project(String title, ProjectStatus status, User user) {
        this.title = title;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

}
