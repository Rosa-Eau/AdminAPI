package com.sparta.classapi.domain.tutor.dto;

import com.sparta.classapi.domain.lecture.entity.Category;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureRequestDto {

    private String name;

    private int cost;

    private String description;

    private Category category;

    private Long tutorId;

    public Lecture toEntity(Tutor tutor) {
        return Lecture.builder()
                .name(name)
                .cost(cost)
                .description(description)
                .category(category)
                .tutor(tutor)
                .build();
    }
}
