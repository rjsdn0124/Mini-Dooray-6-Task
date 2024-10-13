package com.nhnacademy.taskapi.user.service;


import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import com.nhnacademy.taskapi.entity.user.request.UserRequest;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.taskapi.repository.user.UserRepository;
import com.nhnacademy.taskapi.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Save User")
    void saveUser() {
        UserRequest userRequest = new UserRequest("testId");

        when(userRepository.existsById("testId")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(new User("testId"));

        UserDto userDto = userService.save(userRequest);

        assertNotNull(userDto);
        assertEquals("testId", userDto.getUserId());
    }

    @Test
    @DisplayName("Delete User")
    void deleteUser() {
        String userId = "testId2";

        User user = new User(userId);

        when(userRepository.existsById(userId)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserRequest userRequest = new UserRequest(userId);
        userService.save(userRequest);

        when(userRepository.existsById(userId)).thenReturn(true);

        doNothing().when(projectMemberRepository).deleteByUserId(userId);
        doNothing().when(projectRepository).deleteByUserId(userId);

        userService.delete(userId);

        verify(userRepository, times(2)).existsById(userId);
        verify(projectMemberRepository, times(1)).deleteByUserId(userId);
        verify(projectRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    @DisplayName("Get User")
    void getUser() {

        // given
        String userId = "existingUser";
        User user = new User(userId);
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        UserDto result = userService.getUser(userId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        verify(userRepository).findById(userId);
    }
}
