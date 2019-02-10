package com.github.javafaker;

public class Country {
    private final Faker faker;
    private final String flagUrl;

    protected Country(Faker faker) {
        this.faker = faker;
        this.flagUrl = "http://flags.fmcdn.net/data/flags/w580/";
    }

    public String flag() {
        return flagUrl + faker.fakeValuesService().resolve("country.code2", this, faker) + ".png";
    }

    public String country_code2() {
        return faker.fakeValuesService().resolve("country.code2", this, faker);
    }

    public String country_code3() {
        return faker.fakeValuesService().resolve("country.code3", this, faker);
    }

    public String capital() {
        return faker.fakeValuesService().resolve("country.capital", this, faker);
    }
}
