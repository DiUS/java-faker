package com.github.javafaker;

public class Book {
    private final Faker faker;

    protected Book(Faker faker) {
        this.faker = faker;
    }

    public String author() {
        return faker.fakeValuesService().resolve("book.author", this, faker);
    }

    public String title() {
        return faker.fakeValuesService().resolve("book.title", this, faker);
    }

    public String publisher() {
        return faker.fakeValuesService().resolve("book.publisher", this, faker);
    }

    public String genre() {
        return faker.fakeValuesService().resolve("book.genre", this, faker);
    }
}
