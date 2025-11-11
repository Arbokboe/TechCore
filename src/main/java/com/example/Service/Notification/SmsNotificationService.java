package com.example.Service.Notification;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationService {

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Sending SMS to: " + recipient + " with message: " + message);
    }
}
