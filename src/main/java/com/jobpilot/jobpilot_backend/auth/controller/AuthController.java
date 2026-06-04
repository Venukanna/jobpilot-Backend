package com.jobpilot.jobpilot_backend.auth.controller;

import com.jobpilot.jobpilot_backend.auth.dto.AuthResponse;
import com.jobpilot.jobpilot_backend.auth.dto.LoginRequest;
import com.jobpilot.jobpilot_backend.auth.dto.RegisterRequest;
import com.jobpilot.jobpilot_backend.auth.service.AuthService;
import com.jobpilot.jobpilot_backend.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.jobpilot.jobpilot_backend.user.dto.UserResponse;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<Void> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("User registered successfully")
                .data(null)
                .build();
    }
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .build();
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> me() {

        UserResponse user = authService.getCurrentUser();

        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("User details fetched successfully")
                .data(user)
                .build();
    }
}