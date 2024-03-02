package com.sparta.classapi.domain.tutor.repository;

import com.sparta.classapi.domain.tutor.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
