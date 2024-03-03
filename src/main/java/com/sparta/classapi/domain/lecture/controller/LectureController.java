package com.sparta.classapi.domain.lecture.controller;


import com.sparta.classapi.domain.lecture.dto.LectureRequestDto;
import com.sparta.classapi.domain.lecture.service.LectureService;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.global.handler.ValidHelper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping
    public ResponseEntity<?> createLecture(@Valid @RequestBody LectureRequestDto requestDto, BindingResult bindingResult) {
        ValidHelper.validation(bindingResult);
        try {
            return ResponseEntity.ok(lectureService.createLecture(requestDto));
//                    .body(lectureService.createLecture(requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<?> readLecture(@PathVariable Long lectureId) {
        try {
            return ResponseEntity.ok(lectureService.readLecture(lectureId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> readLectureListbyCategory(@RequestParam String category) {
        log.info("들어와 지기는 해?");
        try {
            return ResponseEntity.ok(lectureService.readLectureListbyCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    public ResponseEntity<?> updateLecture(@Valid @PathVariable Long lectureId, @RequestBody LectureRequestDto requestDto, BindingResult bindingResult) {
        ValidHelper.validation(bindingResult);
        try {
            return ResponseEntity.ok()
                    .body(lectureService.updateLecture(lectureId, requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @DeleteMapping("/{lectureId}")
    public ResponseEntity<?> deleteLecture(@PathVariable Long lectureId) {
        try {
            return ResponseEntity.ok()
                    .body(lectureService.deleteLecture(lectureId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
