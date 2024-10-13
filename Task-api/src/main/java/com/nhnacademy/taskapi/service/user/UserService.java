package com.nhnacademy.taskapi.service.user;

import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import com.nhnacademy.taskapi.entity.user.request.UserRequest;

public interface UserService {

    UserDto save(UserRequest userRequest);

    void delete(String userId);

    UserDto getUser(String userId);
}
