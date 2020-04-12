package com.github.javafaker;

public class StarCraft {

    private final Faker faker;

    protected StarCraft(final Faker faker) {
        this.faker = faker;
    }

    public String unit() {
        return faker.fakeValuesService().resolve("starcraft.units", this, faker);
    }

    public String building() {
        return faker.fakeValuesService().resolve("starcraft.buildings", this, faker);
    }

    public String character() {
        return faker.fakeValuesService().resolve("starcraft.characters", this, faker);
    }

    public String planet() {
        return faker.fakeValuesService().resolve("starcraft.planets", this, faker);
    }

}
