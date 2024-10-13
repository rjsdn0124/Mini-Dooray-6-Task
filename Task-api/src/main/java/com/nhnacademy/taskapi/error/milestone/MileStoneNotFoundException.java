package com.nhnacademy.taskapi.error.milestone;

import com.nhnacademy.taskapi.error.NotFoundException;

public class MileStoneNotFoundException extends NotFoundException {

    public MileStoneNotFoundException() {
        super("지정된 마일스톤을 찾을 수 없습니다.");
    }

    public MileStoneNotFoundException(Long mileStoneId) {
        super("선택하신 마일스톤 아이디 : " + mileStoneId + " 의 마일스톤이 존재하지 않습니다.");
    }
}
