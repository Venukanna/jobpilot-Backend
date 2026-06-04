package com.jobpilot.jobpilot_backend.auth.service;

import com.jobpilot.jobpilot_backend.auth.dto.AuthResponse;
import com.jobpilot.jobpilot_backend.auth.dto.LoginRequest;
import com.jobpilot.jobpilot_backend.auth.dto.RegisterRequest;
import com.jobpilot.jobpilot_backend.user.dto.UserResponse;

public interface AuthService {

    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getCurrentUser();
}