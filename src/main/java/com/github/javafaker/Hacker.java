package com.github.javafaker;

public class Hacker {
    private final Faker faker;

    protected Hacker(Faker faker) {
        this.faker = faker;
    }

    public String abbreviation() {
        return faker.fakeValuesService().resolve("hacker.abbreviation", this, faker);
    }

    public String adjective() {
        return faker.fakeValuesService().resolve("hacker.adjective", this, faker);
    }

    public String noun() {
        return faker.fakeValuesService().resolve("hacker.noun", this, faker);
    }

    public String verb() {
        return faker.fakeValuesService().resolve("hacker.verb", this, faker);
    }

    public String ingverb() {
        return faker.fakeValuesService().resolve("hacker.ingverb", this, faker);
    }
}
