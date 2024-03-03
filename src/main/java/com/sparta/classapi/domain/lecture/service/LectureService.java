package com.sparta.classapi.domain.lecture.service;

import com.sparta.classapi.domain.lecture.dto.LectureResponseDto;
import com.sparta.classapi.domain.lecture.entity.Category;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import com.sparta.classapi.domain.lecture.dto.LectureRequestDto;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import com.sparta.classapi.domain.tutor.repository.TutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public LectureService(LectureRepository lectureRepository, TutorRepository tutorRepository) {
        this.lectureRepository = lectureRepository;
        this.tutorRepository = tutorRepository;
    }

    @Transactional
    public LectureResponseDto createLecture(LectureRequestDto requestDto) {

        Category category = Category.valueOf(requestDto.getCategory());

        Tutor tutor = tutorRepository.findById(requestDto.getTutorId()).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        Lecture lecture = lectureRepository.save(requestDto.toEntity(tutor, category));

        return new LectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public LectureResponseDto readLecture(Long lectureId) {

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강의입니다."));

        return new LectureResponseDto(lecture);
    }


    @Transactional(readOnly = true)
    public List<LectureResponseDto> readLectureListbyCategory(String category) {

        Category categoryEnum = Category.valueOf(category);

        List<Lecture> lectures = lectureRepository.findByCategory(categoryEnum);

        return lectures.stream().map(LectureResponseDto::new).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<LectureResponseDto> readLectureListbyTutor(Long tutorId) {

        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        List<Lecture> lectures = lectureRepository.findByTutorOrderByRegisteredAtDesc(tutor);

        if (lectures.isEmpty()) {
            throw new IllegalArgumentException("현재 해당 강사의 강의가 없습니다.");
        }

        return lectures.stream().map(LectureResponseDto::new).collect(Collectors.toList());

    }

    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto) {

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강의입니다."));

        Tutor tutor = tutorRepository.findById(lecture.getTutor().getId()).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 강사입니다."));

        Category category = Category.valueOf(requestDto.getCategory());

        lecture.update(requestDto, tutor, category);

        return new LectureResponseDto(lecture);
    }

    @Transactional
    public Long deleteLecture(Long lectureId) {

        if (!lectureRepository.existsById(lectureId)) {
            throw new IllegalArgumentException("등록되지 않은 강의입니다.");
        }

        lectureRepository.deleteById(lectureId);

        return lectureId;
    }
}
