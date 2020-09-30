package com.github.javafaker;

public class Dessert {

    private final Faker faker;

    protected Dessert(Faker faker) {
        this.faker = faker;
    }

    /**
     * @return dessert variety e.g. "Cake".
     */
    public String variety() {
        return faker.fakeValuesService().resolve("dessert.variety", this, faker);
    }

    /**
     * @return dessert topping e.g. "Rainbow Sprinkles".
     */
    public String topping() {
        return faker.fakeValuesService().resolve("dessert.topping", this, faker);
    }

    /**
     * @return dessert flavor e.g. "Vanilla".
     */
    public String flavor() {
        return faker.fakeValuesService().resolve("dessert.flavor", this, faker);
    }
}
