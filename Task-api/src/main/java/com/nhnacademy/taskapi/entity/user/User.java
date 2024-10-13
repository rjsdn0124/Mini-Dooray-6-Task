
package com.nhnacademy.taskapi.entity.user;

import com.nhnacademy.taskapi.entity.comment.Comment;
import com.nhnacademy.taskapi.entity.projectmember.ProjectMember;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "`user`")
public class User {

    @Id
    @Column(unique = true)
    private String userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;


    public User(String userId) {
        this.userId = userId;
    }
}
