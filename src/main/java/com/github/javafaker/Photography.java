package com.github.javafaker;

/**
 * Generates photography related strings.
 */
public class Photography {

    private final Faker faker;

    protected Photography(Faker faker) {
        this.faker = faker;
    }

    /**
     * @return
     */
    public String term() {
        return faker.fakeValuesService().fetchString("photography.term");
    }

    /**
     * @return
     */
    public String brand() {
        return faker.fakeValuesService().fetchString("photography.brand");
    }

    /**
     * @return
     */
    public String camera() {
        return faker.fakeValuesService().fetchString("photography.camera");
    }

    /**
     * @return
     */
    public String lens() {
        return faker.fakeValuesService().fetchString("photography.lens");
    }

    /**
     * @return
     */
    public String gengre() {
        return faker.fakeValuesService().fetchString("photography.gengre");
    }

    /**
     * @return
     */
    public String imageTag() {
        return faker.fakeValuesService().fetchString("photography.imagetag");
    }

    /**
     * @return
     */
    public String aperture() {
        return faker.fakeValuesService().fetchString("photography.aperture");
    }

    /**
     * @return
     */
    public String shutter() {
        return faker.fakeValuesService().fetchString("photography.shutter");
    }

    /**
     * @return
     */
    public String iso() {
        return faker.fakeValuesService().fetchString("photography.iso");
    }
}