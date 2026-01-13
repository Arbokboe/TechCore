package com.example.user_service.web.service;

import com.example.user_service.web.dto.JwtResponse;
import com.example.user_service.web.dto.AuthRequest;
import com.example.user_service.web.dto.RegisterRequest;
import com.example.user_service.web.dto.UserDto;

public interface AuthService {

    UserDto register(RegisterRequest request);

    JwtResponse login(AuthRequest request);
}

