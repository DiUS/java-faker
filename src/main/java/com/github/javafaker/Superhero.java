package com.github.javafaker;

public class Superhero {
    private final Faker faker;

    protected Superhero(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("superhero.name", this, faker);
    }

    public String prefix() {
        return faker.fakeValuesService().resolve("superhero.prefix", this, faker);
    }

    public String suffix() {
        return faker.fakeValuesService().resolve("superhero.suffix", this, faker);
    }

    public String power() {
        return faker.fakeValuesService().resolve("superhero.power", this, faker);
    }

    public String descriptor() {
        return faker.fakeValuesService().resolve("superhero.descriptor", this, faker);
    }
}
