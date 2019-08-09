package com.github.javafaker;

public class Nation {

    private final Faker faker;

    protected Nation(Faker faker) {
        this.faker = faker;
    }

    public String nationality() {
        return faker.fakeValuesService().resolve("nation.nationality", this, faker);
    }

    public String language() {
        return faker.fakeValuesService().resolve("nation.language", this, faker);
    }

    public String capitalCity() {
        return faker.fakeValuesService().resolve("nation.capital_city", this, faker);
    }

}
