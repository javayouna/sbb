package com.mysite.sbb.repository;

import com.mysite.sbb.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

    //사용자 조회
    Optional<SiteUser> findByusername(String username);
}
