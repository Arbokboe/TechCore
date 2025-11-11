package com.example.Cnotroller;

import com.example.Service.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService emailNotificationService;
    private final NotificationService smsNotificationService;

    @Autowired
    public NotificationController(
            @Qualifier("emailNotificationService") NotificationService emailNotificationService,
            @Qualifier("smsNotificationService") NotificationService smsNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    @GetMapping("/notify/email")
    public String sendEmailNotification(@RequestParam String message, @RequestParam String to) {
        emailNotificationService.sendNotification(message, to);
        return "Email notification sent to: " + to;
    }

    @GetMapping("/notify/sms")
    public String sendSmsNotification(@RequestParam String message, @RequestParam String to) {
        smsNotificationService.sendNotification(message, to);
        return "SMS notification sent to: " + to;
    }

    @GetMapping("/notify/both")
    public String sendBothNotifications(@RequestParam String message, @RequestParam String to) {
        emailNotificationService.sendNotification(message, to);
        smsNotificationService.sendNotification(message, to);
        return "Both notifications sent to: " + to;
    }
}