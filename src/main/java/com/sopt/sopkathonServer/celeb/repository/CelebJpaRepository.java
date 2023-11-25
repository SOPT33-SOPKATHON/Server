package com.sopt.sopkathonServer.celeb.repository;

import com.sopt.sopkathonServer.celeb.domain.Celeb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelebJpaRepository extends JpaRepository<Celeb, Long> {

}
