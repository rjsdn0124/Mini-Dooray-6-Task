package com.nhnacademy.taskapi.controller.user;

import com.nhnacademy.taskapi.entity.user.dto.UserDto;
import com.nhnacademy.taskapi.entity.user.request.UserRequest;
import com.nhnacademy.taskapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")              /* 유저 등록하기 */
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = userService.save(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @DeleteMapping("/user/{userId}")           /* 유저 삭제하기 */
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {
        userService.delete(userId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/user/{userId}")              /* 유저 조회 */
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok().body(userDto);
    }
}
