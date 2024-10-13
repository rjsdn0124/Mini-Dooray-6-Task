package com.nhnacademy.taskapi.error.milestone;

public class InvalidDateRangeException extends RuntimeException {

    public InvalidDateRangeException() {
        super("종료 날짜는 시작 날짜보다 먼저일 수 없습니다.");
    }

    public InvalidDateRangeException(String message) {
        super(message);
    }
}
