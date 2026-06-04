package com.jobpilot.jobpilot_backend.auth.service.impl;

import com.jobpilot.jobpilot_backend.auth.dto.AuthResponse;
import com.jobpilot.jobpilot_backend.auth.dto.LoginRequest;
import com.jobpilot.jobpilot_backend.auth.dto.RegisterRequest;
import com.jobpilot.jobpilot_backend.auth.service.AuthService;
import com.jobpilot.jobpilot_backend.common.exception.BadRequestException;
import com.jobpilot.jobpilot_backend.common.exception.UnauthorizedException;
import com.jobpilot.jobpilot_backend.security.jwt.JwtService;
import com.jobpilot.jobpilot_backend.user.entity.Role;
import com.jobpilot.jobpilot_backend.user.entity.User;
import com.jobpilot.jobpilot_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jobpilot.jobpilot_backend.user.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid email or password"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String accessToken =
                jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken("refresh-token-coming-soon")
                .build();
    }

    @Override
    public UserResponse getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BadRequestException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}