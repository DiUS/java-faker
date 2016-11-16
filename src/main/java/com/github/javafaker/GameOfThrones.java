package com.github.javafaker;

public class GameOfThrones {

    private final Faker faker;

    GameOfThrones(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("game_of_thrones.characters", this, faker);
    }

    public String house() {
        return faker.fakeValuesService().resolve("game_of_thrones.houses", this, faker);
    }

    public String city() {
        return faker.fakeValuesService().resolve("game_of_thrones.cities", this, faker);
    }
}
