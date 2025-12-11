package com.example.NotificationService.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/notifications")
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/notify")
    public ResponseEntity<String> newEventCreate(@RequestParam String message) {
        logger.info(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
