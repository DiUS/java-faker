package com.github.javafaker;

public class Bool {
    private final Faker faker;

    Bool(Faker faker) {
        this.faker = faker;
    }

    public boolean bool() {
        return faker.random().nextBoolean();
    }
}
