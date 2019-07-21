package com.github.javafaker;

public class PrincessBride {
    private final Faker faker;

    protected PrincessBride(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.resolve("princess_bride.characters");
    }

    public String quote() {
        return faker.resolve("princess_bride.quotes");
    }
}
