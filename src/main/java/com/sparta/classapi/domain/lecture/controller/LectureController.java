package com.sparta.classapi.domain.lecture.controller;


import com.sparta.classapi.domain.lecture.dto.LectureRequestDto;
import com.sparta.classapi.domain.lecture.service.LectureService;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "createLecture", description = "강의 등록")
    @PostMapping
    public ResponseEntity<?> createLecture(@Valid @RequestBody LectureRequestDto requestDto, BindingResult bindingResult) {
        try {
            return ResponseEntity.ok(lectureService.createLecture(requestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Tag(name = "readLecture", description = "선택 강의 조회")
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
        try {
            return ResponseEntity.ok(lectureService.readLectureListbyCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    public ResponseEntity<?> updateLecture(@Valid @PathVariable Long lectureId, @RequestBody LectureRequestDto requestDto, BindingResult bindingResult) {
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
