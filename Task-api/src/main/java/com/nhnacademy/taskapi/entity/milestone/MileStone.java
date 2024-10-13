package com.nhnacademy.taskapi.entity.milestone;

import com.nhnacademy.taskapi.entity.project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "milestone", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
})
public class MileStone {

    @Id
    @Column(name = "milestone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mileStoneId;

    @Setter
    @Column(nullable = false,
            length = 100)
    private String title;

    @Setter
    private LocalDate initDate;

    @Setter
    private LocalDate dueDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;


    public MileStone(String title, LocalDate initDate, LocalDate dueDate, Project project) {
        this.title = title;
        this.initDate = initDate;
        this.dueDate = dueDate;
        this.project = project;
    }

}
