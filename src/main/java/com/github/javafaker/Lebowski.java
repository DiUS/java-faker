package com.github.javafaker;

public class Lebowski {
    private final Faker faker;

    public Lebowski(final Faker faker) {
        this.faker = faker;
    }

    public String actor() {
        return faker.fakeValuesService().resolve("lebowski.actors", this, faker);
    }

    public String character() {
        return faker.fakeValuesService().resolve("lebowski.characters", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("lebowski.quotes", this, faker);
    }
}
