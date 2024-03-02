package com.sparta.classapi.domain.tutor.service;

import com.sparta.classapi.domain.lecture.dto.LectureResponseDto;
import com.sparta.classapi.domain.tutor.dto.TutorRequestDto;
import com.sparta.classapi.domain.tutor.dto.TutorResponseDto;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import com.sparta.classapi.domain.tutor.repository.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public TutorResponseDto createTutor(TutorRequestDto requestDto) {

        Tutor tutor = tutorRepository.save(requestDto.toEntity());

        return new TutorResponseDto(tutor);
    }

    public TutorResponseDto readTutor(Long tutorId) {

        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        return new TutorResponseDto(tutor);
    }

    public TutorResponseDto updateTutor(Long tutorId, TutorRequestDto requestDto) {

        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        tutor.update(requestDto);

        return new TutorResponseDto(tutor);
    }

    public Long deleteTutor(Long tutorId) {

        tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        tutorRepository.deleteById(tutorId);

        return tutorId;
    }


}
