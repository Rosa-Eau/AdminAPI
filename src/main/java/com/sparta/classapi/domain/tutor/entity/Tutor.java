package com.sparta.classapi.domain.tutor.entity;

import com.sparta.classapi.domain.tutor.dto.TutorRequestDto;
import com.sparta.classapi.domain.tutor.dto.TutorResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int career;

    @Column
    private String company;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(columnDefinition = "text")
    private String description;

    public void update(TutorRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.description = requestDto.getDescription();
    }
}