package com.nhnacademy.taskapi.user.repo;


import com.nhnacademy.taskapi.entity.user.User;
import com.nhnacademy.taskapi.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final String testUser = "TEST_USER_ID";

    @BeforeEach
    void setUp() {
        // given
        userRepository.save(new User(testUser));
    }

    @Test
    @DisplayName("Save User Test")
    void save() {
        // given
        User saveUser = new User("testSaveId");

        // when
        User user = userRepository.save(saveUser);

        // then
        assertNotNull(user);
        assertEquals("testSaveId", user.getUserId());
    }

    @Test
    @DisplayName("Delete User Test")
    void delete() {
        // when
        userRepository.delete(new User(testUser));

        // then
        assertFalse(userRepository.existsById(testUser));
    }

    @Test
    @DisplayName("Find User Test")
    void findUser() {
        // given
        Optional<User> user = userRepository.findById(testUser);

        // when
        assertTrue(user.isPresent(), "User should be present in the repository");

        // then
        assertEquals(testUser, user.get().getUserId());
    }

    @Test
    @DisplayName("Not Find User Test")
    void notFindUser() {
        // given
        Optional<User> user = userRepository.findById("testUserId2");

        // then
        assertFalse(user.isPresent());
    }

    @Test
    @DisplayName("Check Exist User Test")
    void existUser() {
        // when
        boolean isChecked = userRepository.existsById(testUser);

        // then
        assertTrue(isChecked);
    }

    @Test
    @DisplayName("Check Not Exist User Test")
    void notExistUser() {
        // when
        boolean isChecked = userRepository.existsById("testUserId2");

        // then
        assertFalse(isChecked);
    }
}
