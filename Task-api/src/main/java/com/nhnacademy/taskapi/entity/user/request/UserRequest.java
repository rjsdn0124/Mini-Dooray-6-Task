package com.nhnacademy.taskapi.entity.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String userId;

    public UserRequest() {

    }

    public UserRequest(String userId) {
        this.userId = userId;
    }
}
