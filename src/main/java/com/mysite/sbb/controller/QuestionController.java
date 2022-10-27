package com.mysite.sbb.controller;


import com.mysite.sbb.entity.AnswerForm;
import com.mysite.sbb.entity.Question;
import com.mysite.sbb.entity.QuestionForm;
import com.mysite.sbb.repository.QuestionRepository;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.mysite.sbb.service.QuestionService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor //questionRepository 속성을포함하는 생성자
@Controller
@RequestMapping("/question")
public class QuestionController {



    private final QuestionRepository questionRepository;
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
    @GetMapping("/create") //매개변수로 바인딩한 객체는 Model객체로 전달 안해도 템플릿으로 전달 가능
    public String questionCreate(QuestionForm questionForm){ // th:object QuestionForm 객체가 필요하다.
        return "question_form";
    }
    //질문 DB Save
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){

            if(bindingResult.hasErrors()){
                return "question_form";
            }
            this.questionService.create(questionForm.getSubject(),questionForm.getContent());
        return "redirect:/question/list";
    }

}
