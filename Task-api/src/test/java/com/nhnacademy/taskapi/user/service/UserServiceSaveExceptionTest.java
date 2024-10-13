package com.nhnacademy.taskapi.user.service;

import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.entity.user.request.UserRequest;
import com.nhnacademy.taskapi.error.user.UserAlreadyExistsException;
import com.nhnacademy.taskapi.repository.user.UserRepository;
import com.nhnacademy.taskapi.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
public class UserServiceSaveExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("userId가 null인 경우 에러 발생 확인")
    void saveUserIdIsNullWhenThrowsIllegalArgumentException() {

        // given
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(null);

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.save(userRequest));
        assertEquals("유저 아이디에 올바르지 않은 값이 들어왔습니다.", exception.getMessage());
    }
    
    @Test
    @DisplayName("userId가 empty()인 경우 에러 발생 확인")
    void saveUserIdIsEmptyWhenThrowsIllegalArgumentException() {
        // given
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId("  ");

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.save(userRequest));
        assertEquals("유저 아이디에 올바르지 않은 값이 들어왔습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("동일한 아이디가 save에 접근한 경우 에러 발생 확인")
    void saveWhenUserAlreadyExistsThrowsUserAlreadyExistsException() {
        // given
        String existingUserId = "existingUser";
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(existingUserId);

        when(userRepository.existsById(existingUserId)).thenReturn(true);

        // when & then
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> userService.save(userRequest));
        assertEquals("해당 아이디 (" + existingUserId + ")가 존재합니다.", exception.getMessage());

        verify(userRepository).existsById(existingUserId);
        verify(userRepository, never()).save(any(User.class));
    }
}
