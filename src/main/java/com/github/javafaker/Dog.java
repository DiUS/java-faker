package com.github.javafaker;

public class Dog {

    private final Faker faker;

    protected Dog(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("dog.name", this, faker);
    }

    public String breed() {
        return faker.fakeValuesService().resolve("dog.breed", this, faker);
    }

    public String sound() {
        return faker.fakeValuesService().resolve("dog.sound", this, faker);
    }

    public String memePhrase() {
        return faker.fakeValuesService().resolve("dog.meme_phrase", this, faker);
    }

    public String age() {
        return faker.fakeValuesService().resolve("dog.age", this, faker);
    }

    public String coatLength() {
        return faker.fakeValuesService().resolve("dog.coat_length", this, faker);
    }

    public String gender() {
        return faker.fakeValuesService().resolve("dog.gender", this, faker);
    }

    public String size() {
        return faker.fakeValuesService().resolve("dog.size", this, faker);
    }
}
