package com.nhnacademy.taskapi.user.controller;

import com.nhnacademy.taskapi.entity.user.request.UserRequest;
import com.nhnacademy.taskapi.error.user.UserAlreadyExistsException;
import com.nhnacademy.taskapi.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("POST /api/user - 유저 아이디가 이미 존재할 때 UserAlreadyExistsException")
    void createUser_UserAlreadyExists() throws Exception {
        // given
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId("testUser");

        when(userService.save(any(UserRequest.class)))
                            .thenThrow(new UserAlreadyExistsException("testUser"));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": \"testUser\"}"))
                        .andExpect(status().isConflict());
    }
}
