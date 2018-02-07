package com.github.javafaker;

public class Weather {

    private static final int DEFAULT_MIN_TEMP_C = -30;
    private static final int DEFAULT_MAX_TEMP_C = 38;
    private static final int DEFAULT_MIN_TEMP_F = -22;
    private static final int DEFAULT_MAX_TEMP_F = 100;

    private final Faker faker;

    protected Weather(Faker faker) {
        this.faker = faker;
    }

    public String description() {
        return faker.resolve("weather.description");
    }

    public String temperatureCelsius() {
        return faker.random().nextInt(DEFAULT_MIN_TEMP_C, DEFAULT_MAX_TEMP_C) + faker.resolve("weather.temperature.celsius");
    }

    public String temperatureFahrenheit() {
        return faker.random().nextInt(DEFAULT_MIN_TEMP_F, DEFAULT_MAX_TEMP_F) + faker.resolve("weather.temperature.fahrenheit");
    }
}
