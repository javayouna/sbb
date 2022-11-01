package com.mysite.sbb.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //유일한 값이 들어감
    private String username;

    private String password;

    @Column(unique = true) //유일한 값이 들어감
    private String email;

    private LocalDateTime createDate;
}