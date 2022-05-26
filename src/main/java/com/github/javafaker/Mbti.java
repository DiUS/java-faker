package com.github.javafaker;

public class Mbti {
    private final Faker faker;
    private final String choice;

    public Mbti(final Faker faker) {
        this.faker = faker;
        choice = faker.resolve("mbti.choice");
    }

    public String type() {
        return faker.resolve("mbti.".concat(choice).concat(".type"));
    }

    public String name() {
        return faker.resolve("mbti.".concat(choice).concat(".name"));
    }

    public String characteristic() {
        return faker.resolve("mbti.".concat(choice).concat(".characteristic"));
    }

    public String personage() {
        return faker.resolve("mbti.".concat(choice).concat(".personage"));
    }

    public String merit(){return faker.resolve("mbti.".concat(choice).concat(".merit"));}

    public String weakness(){return faker.resolve("mbti.".concat(choice).concat(".weakness"));}


}
