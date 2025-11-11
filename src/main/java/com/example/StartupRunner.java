package com.example;

import com.example.Service.MessageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final MessageService messageService;

    public StartupRunner(MessageService messageService) {
        this.messageService = messageService;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> CommandLineRunner: приложение запущено");
        String message = messageService.getMessage();
        System.out.println(">>> Сообщение из MessageService: " + message);
    }
}
