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

    public String dish() {
        return faker.fakeValuesService().resolve("food.dish", this, faker);
    }

    public String fruit() {
        return faker.fakeValuesService().resolve("food.fruits", this, faker);
    }

    public String vegetable() {
        return faker.fakeValuesService().resolve("food.vegetables", this, faker);
    }

    public String sushi() {
        return faker.fakeValuesService().resolve("food.sushi", this, faker);
    }

    public String measurement() {
        return faker.fakeValuesService().resolve("food.measurement_sizes", this, faker) +
            " " + faker.fakeValuesService().resolve("food.measurements", this, faker);
    }
}
