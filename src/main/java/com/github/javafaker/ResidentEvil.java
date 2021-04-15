package com.github.javafaker;

public class ResidentEvil {
    private final Faker faker;

    protected ResidentEvil(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("games.resident_evil.characters", this, faker);
    }

    public String biologicalAgent() {
        return faker.fakeValuesService().resolve("games.resident_evil.biological-agents", this, faker);
    }

    public String equipment() {
        return faker.fakeValuesService().resolve("games.resident_evil.equipments", this, faker);
    }

    public String location() {
        return faker.fakeValuesService().resolve("games.resident_evil.locations", this, faker);
    }

    public String creature() {
        return faker.fakeValuesService().resolve("games.resident_evil.creatures", this, faker);
    }
}
