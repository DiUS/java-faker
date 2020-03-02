package com.github.javafaker;

public class Kaamelott {
    private final Faker faker;

    protected Kaamelott(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("kaamelott.characters", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("kaamelott.quotes", this, faker);
    }
}
