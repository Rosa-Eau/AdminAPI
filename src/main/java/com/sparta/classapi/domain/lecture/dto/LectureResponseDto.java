package com.sparta.classapi.domain.lecture.dto;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureResponseDto {
    private String name;

    private int cost;

    private String description;

    private String category;

    private LocalDateTime registeredAt;

    private Long tutorId;


    public LectureResponseDto(Lecture lecture) {
        this.name = lecture.getName();
        this.cost = lecture.getCost();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory().getCategory();
        this.registeredAt = lecture.getRegisteredAt();
        this.tutorId = lecture.getTutor().getId();
    }
}
