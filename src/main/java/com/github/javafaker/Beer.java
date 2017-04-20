package com.github.javafaker;

public class Beer {
    private final Faker faker;

    protected Beer(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("beer.name", this, faker);
    }

    public String style() {
        return faker.fakeValuesService().resolve("beer.style", this, faker);
    }

    public String hop() {
        return faker.fakeValuesService().resolve("beer.hop", this, faker);
    }

    public String yeast() {
        return faker.fakeValuesService().resolve("beer.yeast", this, faker);
    }

    public String malt() {
        return faker.fakeValuesService().resolve("beer.malt", this, faker);
    }
}
