package com.github.javafaker;

public class Money {

    private final Faker faker;

    public Money(Faker faker) {
        this.faker = faker;
    }

    public String currency() {
        return faker.fakeValuesService().resolve("money.currency", this, faker);
    }

    public String currencyCode() {
        return faker.fakeValuesService().resolve("money.code", this, faker);
    }
}
