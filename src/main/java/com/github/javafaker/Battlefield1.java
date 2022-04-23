package com.github.javafaker;

/**
 * CS304 Issue link: https://github.com/DiUS/java-faker/issues/711
 * A class for generating random value of Battlefield1 contents
 * @author coolestjj
 */

public class Battlefield1 {

    private final Faker faker;

    protected Battlefield1(final Faker faker) {
        this.faker = faker;
    }

    /**
     *
     * @return a random unit class name as a string value
     */
    public String classes() {
        return faker.resolve("battlefield1.classes");
    }

    /**
     *
     * @return a random weapon name as a string value
     */
    public String weapon() {
        return faker.resolve("battlefield1.weapon");
    }

    /**
     *
     * @return a random vehicle name as a string value
     */
    public String vehicle() {
        return faker.resolve("battlefield1.vehicle");
    }

    /**
     *
     * @return a random map title as a string value
     */
    public String map() {
        return faker.resolve("battlefield1.map");
    }

    /**
     *
     * @return a random faction name as a string value
     */
    public String faction() {
        return faker.resolve("battlefield1.faction");
    }
}
