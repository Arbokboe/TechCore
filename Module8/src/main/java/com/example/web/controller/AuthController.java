package com.example.web.controller;

import com.example.di.jwt.JwtTokenProvider;
import com.example.di.jwt.dto.jwtResponse;
import com.example.web.dto.AuthRequest;
import com.example.web.dto.RegisterRequest;
import com.example.web.dto.UserDto;
import com.example.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<jwtResponse> login(@RequestBody AuthRequest request) {
        String token = jwtTokenProvider.generateToken(request.getUsername());
        return ResponseEntity.ok(new jwtResponse(token));
    }
}
