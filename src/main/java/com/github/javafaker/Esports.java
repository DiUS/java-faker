package com.github.javafaker;

public class Esports {
    private final Faker faker;

    protected Esports(final Faker faker) {
        this.faker = faker;
    }

    public String player() {
        return faker.resolve("esports.players");
    }

    public String team() {
        return faker.resolve("esports.teams");
    }

    public String event() {
        return faker.resolve("esports.events");
    }

    public String league() {
        return faker.resolve("esports.leagues");
    }

    public String game() {
        return faker.resolve("esports.games");
    }
}
