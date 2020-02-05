package com.github.javafaker;

public class Gender {
    private final Faker faker;

    protected Gender(Faker faker) {
        this.faker = faker;
    }

    public String binaryType() {
        return faker.fakeValuesService().resolve("gender.binary_types", this, faker);
    }
    
    public String shortBinaryType() {
        return faker.fakeValuesService().resolve("gender.short_binary_types", this, faker);
    }

    public String type() {
        return faker.numerify(faker.fakeValuesService().resolve("gender.types", this, faker));
    }
}
