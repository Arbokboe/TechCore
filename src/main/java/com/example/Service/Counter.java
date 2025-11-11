package com.example.Service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Counter {

    private int count = 0;

    public Counter() {
    }

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public int getInstanceHash() {
        return this.hashCode();
    }
}