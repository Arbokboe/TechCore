package com.example.NotificationService.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/notify")
    public ResponseEntity<String> newEventCreate() {
        logger.info("New book create request");
        return ResponseEntity.status(HttpStatus.CREATED).body("New book create request");
    }
}
