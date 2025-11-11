package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final ApplicationContext applicationContext;


    @Autowired
    public CounterService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public String demonstrate() {
        Counter prototypeCounter = applicationContext.getBean(Counter.class);
        prototypeCounter.increment();
        return String.format("счетчик = %d, хэш экземпляра = %d",
                prototypeCounter.getCount(), prototypeCounter.getInstanceHash());
    }
}
