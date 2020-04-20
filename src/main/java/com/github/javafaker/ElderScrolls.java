package com.github.javafaker;

public class ElderScrolls {

    private final Faker faker;

    protected ElderScrolls(Faker faker) {
        this.faker = faker;
    }

    public String race() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.race", this, faker);
    }

    public String creature() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.creature", this, faker);
    }

    public String region() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.region", this, faker);
    }

    public String dragon() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.dragon", this, faker);
    }

    public String city() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.city", this, faker);
    }

    public String firstName() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.first_name", this, faker);
    }

    public String lastName() {
        return faker.fakeValuesService().resolve("games.elder_scrolls.last_name", this, faker);
    }

    public String quote() {return faker.fakeValuesService().resolve("games.elder_scrolls.quote", this, faker);
    }
}
