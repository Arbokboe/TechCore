package com.example.controller;

import com.example.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    public final UserService userService;

    @PostMapping("/register")
    public void newUser () {
    }

}
