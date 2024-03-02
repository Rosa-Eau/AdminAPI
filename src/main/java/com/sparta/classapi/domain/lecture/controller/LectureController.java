package com.sparta.classapi.domain.lecture.controller;


import com.sparta.classapi.domain.lecture.entity.Category;
import com.sparta.classapi.domain.lecture.service.LectureService;
import com.sparta.classapi.domain.tutor.dto.LectureRequestDto;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.global.handler.ValidHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<?> createTutor(@Valid @RequestBody LectureRequestDto requestDto, BindingResult bindingResult) {
        ValidHelper.validation(bindingResult);
        try {
            return ResponseEntity.ok()
                    .body(lectureService.createLecture(requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<?> readTutorInfo(@PathVariable Long lectureId) {
        try {
            return ResponseEntity.ok()
                    .body(lectureService.readLecture(lectureId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readLectureListbyCategory(@RequestParam Category category) {
        try {
            return ResponseEntity.ok().body(lectureService.readLectureListbyCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    public ResponseEntity<?> updateTutorInfo(@Valid @PathVariable Long lectueId, @RequestBody LectureRequestDto requestDto, BindingResult bindingResult) {
        ValidHelper.validation(bindingResult);
        try {
            return ResponseEntity.ok()
                    .body(lectureService.updateLecture(lectueId, requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @DeleteMapping("/{lectureId}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long lectureId) {
        try {
            return ResponseEntity.ok()
                    .body(lectureService.deleteLecture(lectureId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
