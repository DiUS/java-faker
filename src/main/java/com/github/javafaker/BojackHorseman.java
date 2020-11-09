package com.github.javafaker;

/**
 * Generate random parts in BojackHorseman.
 * @author unknown and irakatz
 */

public class BojackHorseman {
    private final Faker faker;

    /**
     * Create a constructor for BojackHorseman.
     * @param faker The Faker instance for generating random parts in BojackHorseman.
     */
    protected BojackHorseman(Faker faker) {
        this.faker = faker;
    }

    /**
     * Generate random character's name in BojackHorseman.
     * @return Characters in BojackHorseman
     */
    public String characters() {
        return faker.fakeValuesService().resolve("bojack_horseman.characters", this, faker);
    }

    /**
     * Generate random quotes in BojackHorseman.
     * @return Quotes in BojackHorseman
     */
    public String quotes() {
        return faker.fakeValuesService().resolve("bojack_horseman.quotes", this, faker);
    }

    /**
     * Generate random tongue twisters in BojackHorseman.
     * @return Tongue twisters in BojackHorseman
     */
    public String tongueTwisters() {
        return faker.fakeValuesService().resolve("bojack_horseman.tongue_twisters", this, faker);
    }

}
