package com.github.javafaker;

import com.github.javafaker.service.RandomService;

public class Bool {

    private final RandomService randomService;

    public Bool(RandomService randomService) {
        this.randomService = randomService;
    }

    public boolean bool() {
        return randomService.nextBoolean();
    }
}
