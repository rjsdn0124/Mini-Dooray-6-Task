package com.nhnacademy.taskapi.controller;

import com.nhnacademy.taskapi.error.AlreadyExistException;
import com.nhnacademy.taskapi.error.ForbiddenException;
import com.nhnacademy.taskapi.error.NotFoundException;
import com.nhnacademy.taskapi.error.milestone.InvalidDateRangeException;
import com.nhnacademy.taskapi.error.projectmember.ProjectCreatorOnlyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, Object>> notFoundExceptionHandler(NotFoundException ne) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", ne.getMessage());
        response.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<Map<String, Object>> alreadyExistsExceptionHandler(AlreadyExistException ae) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", ae.getMessage());
        response.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<Map<String, Object>> projectCreatorExceptionHandler(ProjectCreatorOnlyException pe) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", pe.getMessage());
        response.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler({InvalidDateRangeException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> invalidDateRangeExceptionHandler(Exception ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", ex.getMessage());
        response.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
