package com.github.javafaker;

/**
 * Provides photography related strings.
 */
public class Photography {

    private final Faker faker;

    protected Photography(Faker faker) {
        this.faker = faker;
    }

    /**
     * @return a photography term.
     */
    public String term() {
        return faker.fakeValuesService().fetchString("photography.term");
    }

    /**
     * @return a photography brand.
     */
    public String brand() {
        return faker.fakeValuesService().fetchString("photography.brand");
    }

    /**
     * @return a name of camera model/make.
     */
    public String camera() {
        return faker.fakeValuesService().fetchString("photography.camera");
    }

    /**
     * @return some lens description like 500mm/8.
     */
    public String lens() {
        return faker.fakeValuesService().fetchString("photography.lens");
    }

    /**
     * @return a photography genre.
     */
    public String genre() {
        return faker.fakeValuesService().fetchString("photography.genre");
    }

    /**
     * @return some string to tag an image.
     */
    public String imageTag() {
        return faker.fakeValuesService().fetchString("photography.imagetag");
    }

    /**
     * @return some aperture description like f/1.4 .
     */
    public String aperture() {
        return faker.fakeValuesService().fetchString("photography.aperture");
    }

    /**
     * @return some shutter description like 1/25 .
     */
    public String shutter() {
        return faker.fakeValuesService().fetchString("photography.shutter");
    }

    /**
     * @return some ISO value like 3200.
     */
    public String iso() {
        return faker.fakeValuesService().fetchString("photography.iso");
    }
}
