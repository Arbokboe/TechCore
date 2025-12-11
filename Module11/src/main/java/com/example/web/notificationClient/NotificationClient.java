package com.example.web.notificationClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class NotificationClient {

    private final RestTemplate restTemplate;
    private final String notificationServiceUrl;


    public NotificationClient(
            RestTemplate restTemplate,
            @Value("${notification.service.url}") String notificationServiceUrl) {
        this.restTemplate = restTemplate;
        this.notificationServiceUrl = notificationServiceUrl;
    }


    public void sendNotification(String message) {
        String url = notificationServiceUrl + "api/notifications/notify";
        Map<String, String> request = Map.of("message", message);

        ResponseEntity<String> response = restTemplate.postForEntity(
                url,
                request,
                String.class
        );
    }
}
