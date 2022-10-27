package com.mysite.sbb.controller;


import com.mysite.sbb.entity.AnswerForm;
import com.mysite.sbb.entity.Question;
import com.mysite.sbb.entity.SiteUser;
import com.mysite.sbb.service.AnswerService;
import com.mysite.sbb.service.QuestionService;
import com.mysite.sbb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

private final QuestionService questionService;
private final AnswerService answerService;
private final UserService userService;


    //댓글 등록 + 댓글 null 값 처리
    //Principal principal:현재 로그인한 사용자의 ID
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult,
                               Principal principal)
{
    Question question = this.questionService.getQuestion(id);
    SiteUser siteUser = this.userService.getUser(principal.getName());

    if(bindingResult.hasErrors()){
    model.addAttribute("question", question);
    return "question_detail";
    }

    this.answerService.create(question, answerForm.getContent(),siteUser);
    return String.format("redirect:/question/detail/%s", id);
}
}
