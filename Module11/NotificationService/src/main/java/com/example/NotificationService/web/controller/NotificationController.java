package com.example.NotificationService.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/notify")
    public ResponseEntity<String> newEventCreate(@RequestParam String message) {
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/info")
    public ResponseEntity<String> test(HttpServletRequest request) {
        logger.info(((Integer) request.getLocalPort()).toString());
        return ResponseEntity.ok("OK");
    }
}
