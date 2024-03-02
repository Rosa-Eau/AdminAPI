package com.sparta.classapi.domain.lecture.entity;

import com.sparta.classapi.domain.tutor.dto.LectureRequestDto;
import com.sparta.classapi.domain.tutor.dto.TutorRequestDto;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Lecture")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int cost;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    public void update(LectureRequestDto requestDto, Tutor tutor) {
        this.name = requestDto.getName();
        this.cost = requestDto.getCost();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
        this.tutor = tutor;
    }
}
