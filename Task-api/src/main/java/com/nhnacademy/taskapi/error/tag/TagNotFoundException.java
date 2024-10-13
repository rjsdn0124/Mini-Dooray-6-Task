package com.nhnacademy.taskapi.error.tag;

import com.nhnacademy.taskapi.error.NotFoundException;

public class TagNotFoundException extends NotFoundException {

    public TagNotFoundException() {
      super("지정된 태그를 찾을 수 없습니다.");
    }

    public TagNotFoundException(Long tagId) {
        super("선택한 태그 아이디 : " + tagId + " 의 태그를 찾을 수 없습니다.");
    }
}
