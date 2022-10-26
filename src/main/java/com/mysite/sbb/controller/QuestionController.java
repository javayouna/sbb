package com.mysite.sbb.controller;


import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.mysite.sbb.service.QuestionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequiredArgsConstructor //questionRepository 속성을포함하는 생성자
@Controller
public class QuestionController {



    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    //리스트 
    @RequestMapping("/question/list")
    public String list(Model model){
    List<Question> questionList=this.questionService.getList();
    model.addAttribute("questionList",questionList);
    return "question_list";
}
    //상세보기
    @RequestMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

}
