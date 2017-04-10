package com.github.javafaker;

public class App {
    private final Faker faker;

    protected App(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("app.name", this, faker);
    }

    public String version() {
        return faker.numerify(faker.fakeValuesService().resolve("app.version", this, faker));
    }

    public String author() {
        return faker.fakeValuesService().resolve("app.author", this, faker);
    }
}
