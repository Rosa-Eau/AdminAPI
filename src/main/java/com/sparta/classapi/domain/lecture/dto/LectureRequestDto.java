package com.sparta.classapi.domain.lecture.dto;

import com.sparta.classapi.domain.lecture.entity.Category;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import lombok.Getter;

@Getter
public class LectureRequestDto {

    private String name;

    private int cost;

    private String description;

    private String category;

    private Long tutorId;

    public Lecture toEntity(Tutor tutor, Category category) {
        return Lecture.builder()
                .name(name)
                .cost(cost)
                .description(description)
                .category(category)
                .tutor(tutor)
                .build();
    }
}
