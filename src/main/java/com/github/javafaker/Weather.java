package com.github.javafaker;

public class Weather {

    private final Faker faker;

    protected Weather(Faker faker) {
        this.faker = faker;
    }

    public String description() {
        return faker.resolve("weather.description");
    }
}
