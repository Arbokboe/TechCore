package com.example.web.service;

import com.example.web.dto.RegisterRequest;
import com.example.web.dto.UserDto;

public interface AuthService {

    UserDto register(RegisterRequest request);
}
