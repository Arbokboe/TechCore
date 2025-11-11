package com.example.Cnotroller;

import com.example.Service.BeanLifeCycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LifecycleController {

    private final BeanLifeCycle lifecycleService;

    @Autowired
    public LifecycleController(BeanLifeCycle lifecycleService) {
        this.lifecycleService = lifecycleService;
    }

    @GetMapping("/lifecycle")
    public String getMessage() {
        return lifecycleService.getMessage();
    }
}
