package com.github.javafaker;

public class Space {

    private final Faker faker;

    protected Space(Faker faker) {
        this.faker = faker;
    }

    public String planet() {
        return faker.resolve("space.planet");
    }

    public String moon() {
        return faker.resolve("space.moon");
    }

    public String galaxy() {
        return faker.resolve("space.galaxy");
    }

    public String nebula() {
        return faker.resolve("space.nebula");
    }

    public String starCluster() {
        return faker.resolve("space.star_cluster");
    }

    public String constellation() {
        return faker.resolve("space.constellation");
    }

    public String star() {
        return faker.resolve("space.star");
    }

    public String agency() {
        return faker.resolve("space.agency");
    }

    public String agencyAbbreviation() {
        return faker.resolve("space.agency_abv");
    }

    public String nasaSpaceCraft() {
        return faker.resolve("space.nasa_space_craft");
    }

    public String company() {
        return faker.resolve("space.company");
    }

    public String distanceMeasurement() {
        return faker.number().numberBetween(10, 100) + ' ' + faker.resolve("space.distance_measurement");
    }

    public String meteorite() {
        return faker.resolve("space.meteorite");
    }
}
