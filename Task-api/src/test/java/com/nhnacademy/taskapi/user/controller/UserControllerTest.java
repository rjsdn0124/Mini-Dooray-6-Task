package com.nhnacademy.taskapi.user.controller;

import com.nhnacademy.taskapi.controller.user.UserController;
import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import com.nhnacademy.taskapi.entity.user.request.UserRequest;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("POST /api/user - 유저 등록 성공")
    void createUser_Success() throws Exception {
        // given
        UserDto userDto = new UserDto("testUser");

        when(userService.save(any(UserRequest.class))).thenReturn(userDto);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": \"testUser\"}"))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE /api/user/{userId} - 유저 삭제 성공")
    void deleteUser_Success() throws Exception {
        // given
        String userId = "testUser";

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{userId}", userId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/user/{userId} - 유저 조회 성공")
    void getUser_Success() throws Exception {
        // given
        String userId = "testUser";
        UserDto userDto = new UserDto(userId);

        when(userService.getUser(anyString())).thenReturn(userDto);

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
