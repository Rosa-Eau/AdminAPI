package com.sparta.classapi.domain.user.service;

import com.sparta.classapi.domain.user.dto.SignupRequestDto;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());

        log.info("requestDto : "  + requestDto.toString());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByEmail(email);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }

        UserRoleEnum team = UserRoleEnum.valueOf(requestDto.getTeam());

        // 사용자 등록
        User user = new User(email, password, team);
        userRepository.save(user);
    }
}