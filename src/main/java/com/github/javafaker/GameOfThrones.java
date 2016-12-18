package com.github.javafaker;

public class GameOfThrones {

    private final Faker faker;

    GameOfThrones(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.resolve("game_of_thrones.characters");
    }

    public String house() {
        return faker.resolve("game_of_thrones.houses");
    }

    public String city() {
        return faker.resolve("game_of_thrones.cities");
    }

    public String dragon() {
        return faker.resolve("game_of_thrones.dragons");
    }
}
