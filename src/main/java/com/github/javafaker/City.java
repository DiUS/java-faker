package com.github.javafaker;

public class City {
    private final Faker faker;
    private final Country country;

    protected City(Faker faker) {
        this.faker = faker;
        this.country = new Country(faker);
    }

    public String name(String country) {
        return faker.fakeValuesService().resolve("city."+country, this, faker);
    }

    public String countryAndCityName() {
        String country = faker.fakeValuesService().resolve("country.name", this, faker);
        String city = name(country);

        return country+":"+city;
    }

}
