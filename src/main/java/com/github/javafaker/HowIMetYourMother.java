package com.github.javafaker;

public class HowIMetYourMother {
    private final Faker faker;

    protected HowIMetYourMother(Faker faker) {
        this.faker = faker;
    }

    public String character() {
        return faker.fakeValuesService().resolve("how_i_met_your_mother.character", this, faker);
    }

    public String catchPhrase() {
        return faker.fakeValuesService().resolve("how_i_met_your_mother.catch_phrase", this, faker);
    }

    public String highFive() {
        return faker.fakeValuesService().resolve("how_i_met_your_mother.high_five", this, faker);
    }

    public String quote() {
        return faker.fakeValuesService().resolve("how_i_met_your_mother.quote", this, faker);
    }
}
