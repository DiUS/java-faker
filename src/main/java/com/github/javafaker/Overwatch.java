package com.github.javafaker;

public class Overwatch {
    private final Faker faker;

    protected Overwatch(Faker faker) {
        this.faker = faker;
    }

    public String hero() {
        return faker.fakeValuesService().resolve("overwatch.heroes", this, faker);
    }

    public String location() {
        return faker.fakeValuesService().resolve("overwatch.locations", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("overwatch.quotes", this, faker);
    }
}
