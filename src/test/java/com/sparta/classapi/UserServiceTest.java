package com.sparta.classapi;

import com.sparta.classapi.domain.user.dto.SignupRequestDto;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.repository.UserRepository;
import com.sparta.classapi.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void signup_Success() {
        // Given
        SignupRequestDto requestDto = new SignupRequestDto("test@example.com", "passwOrd123!", "DEVELOPMENT");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        // When
        userService.signup(requestDto);

        // Then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void signup_DuplicateEmail() {
        // Given
        SignupRequestDto requestDto = new SignupRequestDto("test@example.com", "passwOrd123!", "DEVELOPMENT");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new User()));

        // When/Then
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.signup(requestDto)
        );
        org.junit.jupiter.api.Assertions.assertEquals("중복된 이메일이 존재합니다.", exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }
}
