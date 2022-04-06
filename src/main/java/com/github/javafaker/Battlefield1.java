package com.github.javafaker;

public class Battlefield1 {

    private final Faker faker;

    protected Battlefield1(final Faker faker) {
        this.faker = faker;
    }

    public String classes() {
        return faker.resolve("battlefield1.classes");
    }

    public String weapon() {
        return faker.resolve("battlefield1.weapon");
    }

    public String vehicle() {
        return faker.resolve("battlefield1.vehicle");
    }

    public String map() {
        return faker.resolve("battlefield1.map");
    }

    public String faction() {
        return faker.resolve("battlefield1.faction");
    }
}
