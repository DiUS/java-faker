package com.github.javafaker;

public class University {
    private final Faker faker;

    protected University(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("university.name", this, faker);
    }

    public String prefix() {
        return faker.fakeValuesService().resolve("university.prefix", this, faker);
    }

    public String suffix() {
        return faker.fakeValuesService().resolve("university.suffix", this, faker);
    }
}
