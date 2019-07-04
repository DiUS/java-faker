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

    public String countryCode2() {
        return faker.fakeValuesService().resolve("country.code2", this, faker);
    }

    public String countryCode3() {
        return faker.fakeValuesService().resolve("country.code3", this, faker);
    }

    public String capital() {
        return faker.fakeValuesService().resolve("country.capital", this, faker);
    }

    public String currency() {
        return faker.fakeValuesService().resolve("country.currency", this, faker);
    }

    public String currencyCode() {
        return faker.fakeValuesService().resolve("country.currency_code", this, faker);
    }

    public String name() {
        return faker.fakeValuesService().resolve("country.name", this, faker);
    }

}
