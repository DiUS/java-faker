package com.github.javafaker;

public class Food {

    private final Faker faker;

    protected Food(Faker faker) {
        this.faker = faker;
    }

    public String ingredient() {
        return faker.fakeValuesService().resolve("food.ingredients", this, faker);
    }

    public String spice() {
        return faker.fakeValuesService().resolve("food.spices", this, faker);
    }

    public String measurement() {
        return faker.fakeValuesService().resolve("food.measurement_sizes", this, faker) +
            " " + faker.fakeValuesService().resolve("food.measurements", this, faker);
    }
}
