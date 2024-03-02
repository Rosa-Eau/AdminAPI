package com.sparta.classapi.domain.tutor.controller;


import com.sparta.classapi.domain.lecture.service.LectureService;
import com.sparta.classapi.domain.tutor.dto.TutorRequestDto;
import com.sparta.classapi.domain.tutor.service.TutorService;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.global.handler.ValidHelper;
import com.sparta.classapi.global.jwt.JwtUtil;
import com.sparta.classapi.global.security.SecurityUtil;
import com.sparta.classapi.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutor")
@Slf4j
public class TutorController {
    private final TutorService tutorService;
    private final LectureService lectureService;

    public TutorController(TutorService tutorService, LectureService lectureService) {
        this.tutorService = tutorService;
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<?> createTutor(@Valid @RequestBody TutorRequestDto requestDto, BindingResult bindingResult) {
        ValidHelper.validation(bindingResult);
        try {
            return ResponseEntity.ok()
                    .body(tutorService.createTutor(requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<?> readTutorInfo(@PathVariable Long tutorId) {
        try {
            return ResponseEntity.ok()
                    .body(tutorService.readTutor(tutorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{tutorId}/lecture")
    public ResponseEntity<?> findBytutor(@PathVariable Long tutorId) {
        try {
            return ResponseEntity.ok().body(lectureService.readLectureListbyTutor(tutorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/{tutorId}")
    public ResponseEntity<?> updateTutorInfo(@Valid @PathVariable Long tutorId, @RequestBody TutorRequestDto requestDto, BindingResult bindingResult) {
        ValidHelper.validation(bindingResult);
        try {
            return ResponseEntity.ok()
                    .body(tutorService.updateTutor(tutorId, requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @DeleteMapping("/{tutorId}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long tutorId) {
        try {
            return ResponseEntity.ok()
                    .body(tutorService.deleteTutor(tutorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
