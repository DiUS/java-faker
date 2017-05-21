package com.github.javafaker;

public class Job {

    private final Faker faker;

    public Job(final Faker faker) {
        this.faker = faker;
    }

    public String field() {
        return faker.fakeValuesService().resolve("job.field", this, faker);
    }

    public String seniority() {
        return faker.fakeValuesService().resolve("job.seniority", this, faker);
    }

    public String position() {
        return faker.fakeValuesService().resolve("job.position", this, faker);
    }

    public String keySkills() {
        return faker.fakeValuesService().resolve("job.key_skills", this, faker);
    }

    public String title() {
        return faker.fakeValuesService().resolve("job.title", this, faker);
    }
}
