package com.mysite.sbb.entity;

import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Data
public class Question {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동1씩 증가
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    //글쓴이 항목
    @ManyToOne
    private SiteUser author;

}
