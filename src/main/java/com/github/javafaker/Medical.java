package com.github.javafaker;

public class Medical {

    private final Faker faker;

    protected Medical(Faker faker) {
        this.faker = faker;
    }

    public String medicineName() {
        return faker.fakeValuesService().resolve("medical.medicine_name", this, faker);
    }

    public String diseaseName() {
        return faker.fakeValuesService().resolve("medical.disease_name", this, faker);
    }

    public String hospitalName() {
        return faker.fakeValuesService().resolve("medical.hospital_name", this, faker);
    }

    public String symptoms() {
        return faker.fakeValuesService().resolve("medical.symptoms", this, faker);
    }
}
