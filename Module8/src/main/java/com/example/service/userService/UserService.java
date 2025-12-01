package com.example.service.userService;

import com.example.dto.RegisterRequest;
import com.example.dto.UserDto;

public interface UserService {

    UserDto register(RegisterRequest request);
}
