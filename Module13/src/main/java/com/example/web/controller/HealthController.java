package com.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "BookService is UP";
    }

    @GetMapping("/info")
    public String info() {
        return "BookService";
    }
}
