package com.github.javafaker;

import java.time.Duration;

public class FakeDuration {
    private final Faker faker;

    protected FakeDuration(Faker faker){
        this.faker = faker;
    }

    public Duration atMost(Duration minutes){
        return Duration.ofMinutes(30);
    }
}
