package com.sparta.classapi.domain.lecture.repository;

import com.sparta.classapi.domain.lecture.entity.Category;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByCategory(Category category);

    List<Lecture> findByTutorOrderByRegisteredAtDesc(Tutor tutor);

    void deleteByTutorId(Long tutorId);
}
