package com.github.javafaker;

public class HitchhikersGuideToTheGalaxy {
    private final Faker faker;

    protected HitchhikersGuideToTheGalaxy(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.characters", this, faker);
    }

    public String location() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.locations", this, faker);
    }

    public String marvinQuote() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.marvin_quote", this, faker);
    }

    public String planet() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.planets", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.quotes", this, faker);
    }

    public String specie() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.species", this, faker);
    }

    public String starship() {
        return faker.fakeValuesService().resolve("hitchhikers_guide_to_the_galaxy.starships", this, faker);
    }
}
