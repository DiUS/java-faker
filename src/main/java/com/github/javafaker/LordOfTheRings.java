package com.github.javafaker;

public class LordOfTheRings {
    private final Faker faker;

    protected LordOfTheRings(final Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.resolve("lord_of_the_rings.characters");
    }

    public String location() {
        return faker.resolve("lord_of_the_rings.locations");
    }
}
