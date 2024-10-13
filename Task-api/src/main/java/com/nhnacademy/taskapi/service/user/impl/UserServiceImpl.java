package com.nhnacademy.taskapi.service.user.impl;

import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import com.nhnacademy.taskapi.entity.user.request.UserRequest;
import com.nhnacademy.taskapi.error.user.UserAlreadyExistsException;
import com.nhnacademy.taskapi.error.user.UserNotFoundException;
import com.nhnacademy.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.taskapi.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.taskapi.repository.user.UserRepository;
import com.nhnacademy.taskapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public UserDto save(UserRequest userRequest) {

        String userId = userRequest.getUserId();

        if (Objects.isNull(userId) || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("유저 아이디에 올바르지 않은 값이 들어왔습니다.");
        }
        if (userRepository.existsById(userId)) {
            throw new UserAlreadyExistsException(userId);
        }

        User user = userRepository.save(new User(userId));

        return new UserDto(user.getUserId());
    }

    @Override
    public void delete(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디에 올바르지 않은 값이 들어왔습니다.");
        }

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        projectMemberRepository.deleteByUserId(userId);
        projectRepository.deleteByUserId(userId);

        userRepository.delete(new User(userId));
    }

    @Override
    public UserDto getUser(String userId) {
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 아이디에 올바르지 않은 값이 들어왔습니다.");
        }

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return new UserDto(user.getUserId());
    }
}
