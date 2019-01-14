package com.github.javafaker;

public class Dog {

    private final Faker faker;

    protected Dog(Faker faker) {
        this.faker = faker;
    }

    public String name() {
        return faker.fakeValuesService().resolve("creature.dog.name", this, faker);
    }

    public String breed() {
        return faker.fakeValuesService().resolve("creature.dog.breed", this, faker);
    }

    public String sound() {
        return faker.fakeValuesService().resolve("creature.dog.sound", this, faker);
    }

    public String memePhrase() {
        return faker.fakeValuesService().resolve("creature.dog.meme_phrase", this, faker);
    }

    public String age() {
        return faker.fakeValuesService().resolve("creature.dog.age", this, faker);
    }

    public String coatLength() {
        return faker.fakeValuesService().resolve("creature.dog.coat_length", this, faker);
    }

    public String gender() {
        return faker.fakeValuesService().resolve("creature.dog.gender", this, faker);
    }

    public String size() {
        return faker.fakeValuesService().resolve("creature.dog.size", this, faker);
    }
}
