package com.github.javafaker;

public class Compass {
    private final Faker faker;

    protected Compass(Faker faker) {
        this.faker = faker;
    }

    public String direction() {
        return faker.expression("#{compass.direction}");
    }

    public String abbreviation() {
        return faker.expression("#{compass.abbreviation}");
    }
    
    public String azimuth() {
        return faker.expression("#{compass.azimuth}");
    }
    
}