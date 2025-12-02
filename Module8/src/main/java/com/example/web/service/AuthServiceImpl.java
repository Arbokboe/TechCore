package com.example.web.service;

import com.example.datasource.model.Role;
import com.example.datasource.model.User;
import com.example.web.dto.RegisterRequest;
import com.example.web.dto.UserDto;
import com.example.datasource.mapper.UserMapper;
import com.example.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto register(RegisterRequest request) {
        User user = userMapper.toEntity(request);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        return userMapper.toDto(userRepository.save(user));
    }
}
