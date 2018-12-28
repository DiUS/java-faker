package com.github.javafaker;

public class Pokemon {

    private final Faker faker;

    protected Pokemon(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.resolve("games.pokemon.names");
    }

    public String location() {
        return faker.resolve("games.pokemon.locations");
    }
}
