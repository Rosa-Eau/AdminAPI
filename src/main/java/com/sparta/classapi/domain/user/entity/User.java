package com.sparta.classapi.domain.user.entity;

import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String team;

    @Column(nullable = false)
    // Enum 을 데이터베이스에 저장
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String password, String team, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}