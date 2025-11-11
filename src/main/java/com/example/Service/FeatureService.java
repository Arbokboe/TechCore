package com.example.Service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "feature.toggle.enable", havingValue = "true")
public class FeatureService {

    public FeatureService() {
        System.out.println(">>> FeatureService: бин создан (feature.toggle.enable=true)");
    }

    public String getFeatureMessage() {
        return "Условный функционал активен";
    }
}
