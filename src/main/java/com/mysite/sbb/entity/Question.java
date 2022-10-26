package com.mysite.sbb.entity;


<<<<<<< HEAD
=======
import lombok.Data;
>>>>>>> 412e084 (Initial commit)
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
<<<<<<< HEAD
=======
@Data
>>>>>>> 412e084 (Initial commit)
public class Question {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동1씩 증가
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

}
