package com.nhnacademy.taskapi.entity.milestone.request;


import com.nhnacademy.taskapi.error.milestone.InvalidDateRangeException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class MileStoneRequest {
    private String title;
    private LocalDate initDate;
    private LocalDate dueDate;
    private long projectId;

    public MileStoneRequest() {
    }

    public MileStoneRequest(String title, LocalDate initDate, LocalDate dueDate, Long projectId) {

        if (Objects.nonNull(initDate) && Objects.nonNull(dueDate)) {
            if (dueDate.isBefore(initDate)) {
                throw new InvalidDateRangeException();
            }
        }

        this.title = title;
        this.initDate = initDate;
        this.dueDate = dueDate;
        this.projectId = projectId;
    }
}
