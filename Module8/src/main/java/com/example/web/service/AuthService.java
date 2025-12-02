package com.example.web.service;

import com.example.di.jwt.dto.JwtResponse;
import com.example.web.dto.AuthRequest;
import com.example.web.dto.RegisterRequest;
import com.example.web.dto.UserDto;

public interface AuthService {

    UserDto register(RegisterRequest request);

    JwtResponse login(AuthRequest request);
}
