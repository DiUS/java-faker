package com.github.javafaker;

public class Educator {
    private final Faker faker;

    protected Educator(Faker faker) {
        this.faker = faker;
    }

    // TODO - move these all out to en.yml by default. 
    public String university() {
        return faker.fakeValuesService().resolve("educator.name", this, faker) 
                + " " 
                + faker.fakeValuesService().resolve("educator.tertiary.type", this, faker);
    }

    public String course() {
        return faker.fakeValuesService().resolve("educator.tertiary.course.type", this, faker) 
                + " "
                + faker.fakeValuesService().resolve("educator.tertiary.course.subject", this, faker);
    }

    public String secondarySchool() {
        return faker.fakeValuesService().resolve("educator.name", this, faker)
                + " "
                + faker.fakeValuesService().resolve("educator.secondary", this, faker);
    }

    public String campus() {
        return faker.fakeValuesService().resolve("educator.name", this, faker) + " Campus";
    }

}
