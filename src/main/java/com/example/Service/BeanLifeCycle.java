package com.example.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class BeanLifeCycle {

    @PostConstruct
    public void init() {
        System.out.println(">>> LifecycleService: инициализация бина с помощью @PostConstruct");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println(">>> LifecycleService: уничтожение бина с помощью @PreDestroy");
    }

    public String getMessage() {
        return "Service is working";
    }
}
