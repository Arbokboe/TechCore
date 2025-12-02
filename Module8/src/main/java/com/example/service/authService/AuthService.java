package com.example.service.authService;

import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;

public interface AuthService {

    UserDto register(RegisterRequest request);
}
