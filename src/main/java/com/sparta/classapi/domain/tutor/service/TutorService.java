package com.sparta.classapi.domain.tutor.service;

import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import com.sparta.classapi.domain.tutor.dto.TutorRequestDto;
import com.sparta.classapi.domain.tutor.dto.TutorResponseDto;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import com.sparta.classapi.domain.tutor.repository.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TutorService {

    private final TutorRepository tutorRepository;
    private final LectureRepository lectureRepository;

    public TutorService(TutorRepository tutorRepository, LectureRepository lectureRepository) {
        this.tutorRepository = tutorRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public TutorResponseDto createTutor(TutorRequestDto requestDto) {

        Tutor tutor = tutorRepository.save(requestDto.toEntity());

        return new TutorResponseDto(tutor);
    }

    @Transactional(readOnly = true)
    public TutorResponseDto readTutor(Long tutorId) {

        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        return new TutorResponseDto(tutor);
    }

    @Transactional
    public TutorResponseDto updateTutor(Long tutorId, TutorRequestDto requestDto) {

        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        tutor.update(requestDto);

        return new TutorResponseDto(tutor);
    }

    @Transactional
    public Long deleteTutor(Long tutorId) {

        tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        lectureRepository.deleteByTutorId(tutorId);

        tutorRepository.deleteById(tutorId);

        return tutorId;
    }


}
