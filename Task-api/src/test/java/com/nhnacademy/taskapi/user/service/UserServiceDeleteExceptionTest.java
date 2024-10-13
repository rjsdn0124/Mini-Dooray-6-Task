package com.nhnacademy.taskapi.user.service;

import com.nhnacademy.taskapi.error.user.UserNotFoundException;
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
import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
public class UserServiceDeleteExceptionTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("userId가 null일 때 IllegalArgumentException 발생 테스트")
    void deleteUserIdIsNullWhenThrowsIllegalArgumentException() {
        // given
        String userId = null;

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.delete(userId));
        assertEquals("유저 아이디에 올바르지 않은 값이 들어왔습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("userId가 빈 문자열일 때 IllegalArgumentException 발생 테스트")
    void deleteUserIdIsEmptyWhenThrowsIllegalArgumentException() {
        // given
        String userId = "";

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.delete(userId));
        assertEquals("유저 아이디에 올바르지 않은 값이 들어왔습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("userId가 존재하지 않을 때 UserNotFoundException 발생 테스트")
    void deleteUserNotExistsWhenThrowsUserNotFoundException() {
        // given
        String userId = "nonExistentUser";
        when(userRepository.existsById(userId)).thenReturn(false);

        // when & then
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.delete(userId));
        assertEquals("유저 아이디 : " + userId + " 로 등록된 유저를 찾을 수 없습니다.", exception.getMessage());
    }
}
