package com.github.javafaker;

public class Rajinikanth {
    private final Faker faker;

    protected Rajinikanth(Faker faker) {
        this.faker = faker;
    }

    public String fact() {
        return faker.fakeValuesService().resolve("rajinikanth.fact", this, faker);
    }

}
