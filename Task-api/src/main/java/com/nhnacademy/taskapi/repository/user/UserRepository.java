package com.nhnacademy.taskapi.repository.user;

import com.nhnacademy.taskapi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
