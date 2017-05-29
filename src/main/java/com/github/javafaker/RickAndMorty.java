package com.github.javafaker;

public class RickAndMorty  {
    private final Faker faker;

    protected RickAndMorty(final Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.resolve("rick_and_morty.characters");
    }

    public String location() {
        return faker.resolve("rick_and_morty.locations");
    }

    public String quote() {
        return faker.resolve("rick_and_morty.quotes");
    }
}
