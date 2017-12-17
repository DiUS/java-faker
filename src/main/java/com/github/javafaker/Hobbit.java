package com.github.javafaker;

public class Hobbit {
    private final Faker faker;

    protected Hobbit(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("hobbit.character", this, faker);
    }

    public String thorinsCompany() {
        return faker.fakeValuesService().resolve("hobbit.thorins_company", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("hobbit.quote", this, faker);
    }

    public String location() {
        return faker.fakeValuesService().resolve("hobbit.location", this, faker);
    }
}
