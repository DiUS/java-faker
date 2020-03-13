package com.github.javafaker;

/**
 * Generates coin sides.
 */
public class Coin {

    private final Faker faker;

    protected Coin(Faker faker) {
        this.faker = faker;
    }

    /**
     * @return coin side e.g. "Heads" or "Tails".
     */
    public String flip() {
        return faker.fakeValuesService().resolve("coin.flip", this, faker);
    }
}
