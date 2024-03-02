package com.sparta.classapi.domain.lecture.dto;


import com.sparta.classapi.domain.lecture.entity.Category;
import com.sparta.classapi.domain.lecture.entity.Lecture;

import java.time.LocalDateTime;

public class LectureResponseDto {
    private String name;

    private int cost;

    private String description;

    private Category category;

    private LocalDateTime registeredAt;

    private Long tutorId;


    public LectureResponseDto(Lecture lecture) {
        this.name = lecture.getName();
        this.cost = lecture.getCost();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.registeredAt = lecture.getRegisteredAt();
        this.tutorId = lecture.getTutor().getId();
    }
}
