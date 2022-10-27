package com.mysite.sbb.controller;


import com.mysite.sbb.entity.AnswerForm;
import com.mysite.sbb.entity.Question;
import com.mysite.sbb.entity.QuestionForm;
import com.mysite.sbb.entity.SiteUser;
import com.mysite.sbb.repository.QuestionRepository;
import com.mysite.sbb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.mysite.sbb.service.QuestionService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
@RequiredArgsConstructor //questionRepository 속성을포함하는 생성자
@Controller
@RequestMapping("/question")
public class QuestionController {



    private final QuestionRepository questionRepository;
    private final UserService userService;
    private final QuestionService questionService;

    //리스트 
    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value="page",defaultValue = "0") int page){
    Page<Question> paging=this.questionService.getlist(page);
    model.addAttribute("paging",paging);
    return "question_list";
}
    //상세보기
    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    //질문 작성
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create") //매개변수로 바인딩한 객체는 Model객체로 전달 안해도 템플릿으로 전달 가능
    public String questionCreate(QuestionForm questionForm){ // th:object QuestionForm 객체가 필요하다.
        return "question_form";
    }
    //질문 DB Save
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult,
            Principal principal){

            if(bindingResult.hasErrors()){
                return "question_form";
            }
        SiteUser siteUser = this.userService.getUser(principal.getName());

            this.questionService.create(questionForm.getSubject(),questionForm.getContent(),siteUser);
        return "redirect:/question/list";
    }

    //수정 버튼
    @PreAuthorize("istAuthenticated()")
    @GetMapping("modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id")
                                 Integer id, Principal principal){
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    }

