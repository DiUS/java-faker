package com.github.javafaker;

public class Zelda {
    private final Faker faker;

    protected Zelda(final Faker faker) {
        this.faker = faker;
    }

    public String game() {
        return faker.resolve("games.zelda.games");
    }

    public String character() {
        return faker.resolve("games.zelda.characters");
    }
}
