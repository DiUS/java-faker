package com.github.javafaker;

public class Babylon5 {
    private final Faker faker;

    protected Babylon5(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.resolve("babylon5.characters");
    }

    public String quote() {
        return faker.resolve("babylon5.quotes");
    }
}
