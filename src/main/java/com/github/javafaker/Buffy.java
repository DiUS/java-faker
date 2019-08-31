package com.github.javafaker;

public class Buffy {
    private final Faker faker;

    protected Buffy(Faker faker) {
        this.faker = faker;
    }

    public String characters() {
        return faker.fakeValuesService().resolve("buffy.characters", this, faker);
    }

    public String quotes() {
        return faker.fakeValuesService().resolve("buffy.quotes", this, faker);
    }

    public String celebrities() {
        return faker.fakeValuesService().resolve("buffy.celebrities", this, faker);
    }

    public String bigBads() {
        return faker.fakeValuesService().resolve("buffy.big_bads", this, faker);
    }

    public String episodes() {
        return faker.fakeValuesService().resolve("buffy.episodes", this, faker);
    }

}
