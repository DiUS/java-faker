package com.github.javafaker;

public class Mountain {
    private final Faker faker;

    protected Mountain(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("mountain.name", this, faker);
    }

    public String range() {
        return faker.fakeValuesService().resolve("mountain.range", this, faker);
    }
}
