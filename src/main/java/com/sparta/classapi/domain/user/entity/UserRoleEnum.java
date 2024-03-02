package com.sparta.classapi.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    DEVELOPMENT(Authority.MANAGER),
    CURRICULUM(Authority.MANAGER),
    MARKETING(Authority.STAFF);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String STAFF = "ROLE_STAFF";
        public static final String MANAGER = "ROLE_MANAGER";
    }
}