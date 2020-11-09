package com.github.javafaker;

/**
 * Generate random, different kinds of disease.
 */
public class Disease {
    private final Faker faker;

    /**
     * Create a constructor for Disease
     * @param faker The Faker instance for generating random, different kinds of disease, e.g. the internal disease.
     */
    protected Disease(Faker faker) {
        this.faker = faker;
    }

    /**
     * Generate random internal disease
     * @return An internal disease
     */
    public String internalDisease() {
        return faker.fakeValuesService().resolve("disease.internal_disease", this, faker);
    }

    /**
     * Generate random neurology disease
     * @return A neurology disease
     */
    public String neurology() {
        return faker.fakeValuesService().resolve("disease.neurology", this, faker);
    }

    /**
     * Generate random surgery disease
     * @return A surgery disease
     */
    public String surgery() {
        return faker.fakeValuesService().resolve("disease.surgery", this, faker);
    }

    /**
     * Generate random paediattics disease
     * @return A paediatrics disease
     */
    public String paediatrics() {
        return faker.fakeValuesService().resolve("disease.paediatrics", this, faker);
    }

    /**
     * Generate random gynecology and obstetrics disease
     * @return A gynecology and obstetrics disease
     */
    public String gynecologyAndObstetrics() {
        return faker.fakeValuesService().resolve("disease.gynecology_and_obstetrics", this, faker);
    }

    /**
     * Generate random ophthalmology and otorhinolaryngology disease
     * @return A ophthalmology and otorhinolaryngology disease
     */
    public String ophthalmologyAndOtorhinolaryngology() {
        return faker.fakeValuesService().resolve("disease.ophthalmology_and_otorhinolaryngology", this, faker);
    }

    /**
     * Generate random dermatolory disease
     * @return A dermatolory disease
     */
    public String dermatolory() {
        return faker.fakeValuesService().resolve("disease.dermatolory", this, faker);
    }

}
