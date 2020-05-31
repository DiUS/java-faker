package com.github.javafaker;

public class Basketball {
    private final Faker faker;

    /**
     * Create a constructor for Basketball.
     * @param faker The Faker instance for generating random, different kinds of disease, e.g. the internal disease.
     */
    protected Basketball(Faker faker) {
        this.faker = faker;
    }

    /**
     * Generate random coaches
     * @return coaches
     */
    public String coaches() {
        return faker.fakeValuesService().resolve("basketball.coaches", this, faker);
    }

    /**
     * Generate random teams
     * @return teams
     */
    public String teams() {
        return faker.fakeValuesService().resolve("basketball.teams", this, faker);
    }



    /**
     * Generate random positions
     * @return positions
     */
    public String positions() {
        return faker.fakeValuesService().resolve("basketball.positions", this, faker);
    }

    /**
     * Generate random players
     * @return players
     */

    public String players() {
        return faker.fakeValuesService().resolve("basketball.players", this, faker);
    }
}
