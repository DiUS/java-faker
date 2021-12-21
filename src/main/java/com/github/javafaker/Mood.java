package com.github.javafaker;

public class Mood {

    private final Faker faker;

    protected Mood(Faker faker) {
        this.faker = faker;
    }

    public String feeling(){
        return faker.fakeValuesService().resolve("mood.feeling", this, faker);
    }

    public String emotion(){
        return faker.fakeValuesService().resolve("mood.emotion", this, faker);
    }

    public String tone(){
        return faker.fakeValuesService().resolve("mood.tone", this, faker);
    }

}
