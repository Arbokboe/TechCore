package com.example.Cnotroller;

import com.example.Service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }


    @GetMapping("/counter/demonstrate")
    public String demonstrateCorrectPrototypeUsage() {
        return counterService.demonstrate();
    }


}
