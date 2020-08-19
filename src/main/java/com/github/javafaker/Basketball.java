package com.github.javafaker;

/**
 * Generate random components of basketall game, e.g. teams, coaches, positions and players.
 * @author unknown and irakatz
 */
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
     * Generate random basketball teams
     * @return Basketball teams
     */
    public String teams() {
        return faker.fakeValuesService().resolve("basketball.teams", this, faker);
    }

    /**
     * Generate random coaches in basketball game
     * @return Basketball coaches
     */
    public String coaches() {
        return faker.fakeValuesService().resolve("basketball.coaches", this, faker);
    }

    /**
     * Generate random positions in basketball game
     * @return Basketball positions
     */
    public String positions() {
        return faker.fakeValuesService().resolve("basketball.positions", this, faker);
    }

    /**
     * Generate random basketball players
     * @return Basketball players
     */
    public String players() {
        return faker.fakeValuesService().resolve("basketball.players", this, faker);
    }
}
