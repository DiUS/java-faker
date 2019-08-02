package com.github.javafaker;

public class Currency {

    private final Faker faker;

    public Currency(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("currency.name", this, faker);
    }

    public String code() {
        return faker.fakeValuesService().resolve("currency.code", this, faker);
    }
}
