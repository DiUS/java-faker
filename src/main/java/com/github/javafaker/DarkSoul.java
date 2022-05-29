package com.github.javafaker;

/**
 * Add DarkSoul element in the project.
 * Issue link: https://github.com/DiUS/java-faker/issues/722
 * @author SickDawn
 */
public class DarkSoul {
    private final Faker faker;

    public DarkSoul(final Faker faker) {
        this.faker = faker;
    }

    public String stats(){
        return faker.resolve("darkSoul.stats");
    }

    public String covenants(){
        return faker.resolve("darkSoul.covenants");
    }

    public String classes(){
        return faker.resolve("darkSoul.classes");
    }

    public String weapon(){
        return faker.resolve("darkSoul.weapon");
    }

    public String shield(){
        return faker.resolve("darkSoul.shield");
    }
}
