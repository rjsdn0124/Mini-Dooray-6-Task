package com.nhnacademy.taskapi.entity.milestone.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MileStoneDto {

    private String title;
    private LocalDate initDate;
    private LocalDate dueDate;

    public MileStoneDto() {
    }

    public MileStoneDto(String title, LocalDate initDate, LocalDate dueDate) {
        this.title = title;
        this.initDate = initDate;
        this.dueDate = dueDate;
    }
}
