package com.sparta.classapi.domain.lecture.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    SPRING("SPRING"), REACT("REACT"), NODE("NODE");
    private final String category;
}