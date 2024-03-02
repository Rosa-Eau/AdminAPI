package com.sparta.classapi.domain.tutor.dto;

import com.sparta.classapi.domain.tutor.entity.Tutor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class TutorRequestDto {

    private String name;

    private int career;

    private String company;

    private String phoneNumber;

    private String description;

    public Tutor toEntity() {
        return Tutor.builder()
                .name(this.name)
                .career(this.career)
                .company(this.company)
                .phoneNumber(this.phoneNumber)
                .description(this.description)
                .build();
    }
}
