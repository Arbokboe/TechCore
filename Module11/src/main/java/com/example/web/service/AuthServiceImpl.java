package com.example.web.service;

import com.example.datasource.model.Role;
import com.example.datasource.model.User;
import com.example.di.jwt.JwtTokenProvider;
import com.example.di.jwt.dto.JwtResponse;
import com.example.web.dto.AuthRequest;
import com.example.web.dto.RegisterRequest;
import com.example.web.dto.UserDto;
import com.example.datasource.mapper.UserMapper;
import com.example.datasource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RestTemplate restTemplate;

    public UserDto register(RegisterRequest request) {
        User user = userMapper.toEntity(request);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        ResponseEntity<String> response = callNotificationService("New register request from user: " + user.getUsername());
        return userMapper.toDto(userRepository.save(user));
    }

    public JwtResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        return new JwtResponse(jwtTokenProvider.generateToken(request.getUsername()));
    }

    public ResponseEntity<String> callNotificationService(String message) {
        String url = UriComponentsBuilder
                .fromUriString("http://NOTIFICATION-SERVICE/api/notifications/notify")
                .queryParam("message", message)
                .build()
                .toUriString();

        System.out.println("Url request: " + url);

        return restTemplate.postForEntity(
                url,
                null,
                String.class
        );
    }
}
