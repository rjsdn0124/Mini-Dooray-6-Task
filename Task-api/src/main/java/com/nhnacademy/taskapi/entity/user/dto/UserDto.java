package com.nhnacademy.taskapi.entity.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userId;

    public UserDto() {}

    public UserDto(String userId) {
        this.userId = userId;
    }
}
