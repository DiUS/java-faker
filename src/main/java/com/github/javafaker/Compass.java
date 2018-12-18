package com.github.javafaker;

public class Compass {
    private final Faker faker;

    protected Compass(Faker faker) {
        this.faker = faker;
    }

    public String directions() {
        return faker.expression("#{compass.direction}");
    }
}