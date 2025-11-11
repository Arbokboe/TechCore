package com.example.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value("${app.greeting}")
    private String greetingMessage;

    public String getMessage() {
        return greetingMessage;
    }
}