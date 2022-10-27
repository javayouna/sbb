package com.mysite.sbb.entity;


import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Data
public class AnswerForm {
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;
}
