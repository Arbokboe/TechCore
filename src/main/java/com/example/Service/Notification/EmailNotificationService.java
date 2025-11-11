package com.example.Service.Notification;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending EMAIL to: " + recipient + " with message: " + message);
    }
}
