package com.mysite.sbb.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class QuestionForm {

@NotEmpty(message = "제목을 입력해주세요")
@Size(max=200) //200byte
private String subject;

@NotEmpty(message = "내용을 입력해주세요")
private String content;
}

