package com.example.web.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationClient {

    @PostMapping("/api/notifications/notify")
    ResponseEntity<String> sendNotification(@RequestParam("message") String message);

    @GetMapping("/api/notifications/info")
    ResponseEntity<String> test(@RequestParam("message") String message);
}
