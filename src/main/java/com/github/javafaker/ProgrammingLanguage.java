package com.github.javafaker;

public class ProgrammingLanguage {

    private final Faker faker;

    public ProgrammingLanguage(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("programming_language.name", this, faker);
    }

    public String creator() {
        return faker.fakeValuesService().resolve("programming_language.creator", this, faker);
    }

}
