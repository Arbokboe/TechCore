package com.example.user_service.web.service;

import com.example.user_service.datasource.mapper.UserMapper;
import com.example.user_service.datasource.model.Role;
import com.example.user_service.datasource.model.User;
import com.example.user_service.datasource.repository.UserRepository;
import com.example.user_service.exception.BadCredentials;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.jwt.JwtGenerator;
import com.example.user_service.web.dto.JwtResponse;
import com.example.user_service.web.dto.AuthRequest;
import com.example.user_service.web.dto.RegisterRequest;
import com.example.user_service.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtGenerator jwtGenerator;

    public UserDto register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new BadCredentials("User by " + request.getUsername() + " exist");

        User userEntity = userMapper.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(Role.USER);
        return userMapper.toDto(userRepository.save(userEntity));
    }

    public JwtResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()
                -> new UserNotFoundException("User " + request.getUsername() + " not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentials("Bad credentials");

        return new JwtResponse(jwtGenerator.generateToken(user.getUsername()));
    }
}
