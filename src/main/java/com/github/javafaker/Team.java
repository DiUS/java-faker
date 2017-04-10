package com.github.javafaker;

public class Team {
    private final Faker faker;

    protected Team(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("team.name", this, faker);
    }

    public String creature() {
        return faker.fakeValuesService().resolve("team.creature", this, faker);
    }

    public String state() {
        return faker.fakeValuesService().resolve("address.state", this, faker);
    }

    public String sport() {
        return faker.fakeValuesService().resolve("team.sport", this, faker);
    }
}
