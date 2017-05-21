package com.github.javafaker;

public class Friends {
    private final Faker faker;

    protected Friends(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.resolve("friends.characters");
    }

    public String location() {
        return faker.resolve("friends.locations");
    }

    public String quote() {
        return faker.resolve("friends.quotes");
    }
}