package com.github.javafaker;

import com.github.javafaker.service.RandomService;

public class Options {
    private final RandomService randomService;

    public Options(RandomService randomService) {
        this.randomService = randomService;
    }

    public String option(String[] options) {
        return options[randomService.nextInt(options.length)];
    }
}