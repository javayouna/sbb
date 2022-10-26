package com.mysite.sbb.service;


import com.mysite.sbb.entity.Question;
import com.mysite.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    //전체리스트
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

//상세보기
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get();
        } else {
           throw new DataNotFoundException("question not found");
        }

    }
}
