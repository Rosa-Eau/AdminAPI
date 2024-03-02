package com.sparta.classapi.domain.tutor.dto;


import com.sparta.classapi.domain.tutor.entity.Tutor;
import lombok.Getter;

@Getter
public class TutorResponseDto {

    private String name;

    private int career;

    private String company;

    private String phoneNumber;

    private String description;
    public TutorResponseDto(Tutor tutor) {
        this.name = tutor.getName();
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.description = tutor.getDescription();
    }

}
