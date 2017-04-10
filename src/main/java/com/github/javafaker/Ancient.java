package com.github.javafaker;

public class Ancient {

    private final Faker faker;

    protected Ancient(Faker faker) {
        this.faker = faker;
    }

    public String god() {
        return faker.resolve("ancient.god");
    }

    public String primordial() {
        return faker.resolve("ancient.primordial");
    }

    public String titan() {
        return faker.resolve("ancient.titan");
    }

    public String hero() {
        return faker.resolve("ancient.hero");
    }
}
