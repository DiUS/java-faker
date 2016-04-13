package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;

public class Address {

    private final Resolver resolver;
    private final Name name;
    private final FakeValuesServiceInterface fakeValuesService;
    private final RandomService randomService;

    public Address(Resolver resolver, Name name, FakeValuesServiceInterface fakeValuesService, RandomService randomService) {
        this.resolver = resolver;
        this.name = name;
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    public String streetName() {
        return resolve("address.street_name");
    }

    public String streetAddressNumber() {
        return resolve("address.street_address");
    }

    public String streetAddress() {
        return resolve("address.street_address");
    }

    public String streetAddress(boolean includeSecondary) {
        String streetAddress = resolve("address.street_address");
        if (includeSecondary) {
            streetAddress = streetAddress + " " + secondaryAddress();
        }
        return streetAddress;
    }

    public String secondaryAddress() {
        return fakeValuesService.numerify(fakeValuesService.fetchString("address.secondary_address"));
    }

    public String zipCode() {
        return fakeValuesService.bothify(fakeValuesService.fetchString("address.postcode"));
    }

    public String streetSuffix() {
        return fakeValuesService.fetchString("address.street_suffix");
    }

    public String citySuffix() {
        return fakeValuesService.safeFetch("address.city_suffix");
    }

    public String cityPrefix() {
        return fakeValuesService.safeFetch("address.city_prefix");
    }

    public String city() {
        return resolve("address.city");
    }

    public String cityName() {
        return resolve("address.city_name");
    }

    public String state() {
        return fakeValuesService.fetchString("address.state");
    }

    public String stateAbbr() {
        return fakeValuesService.fetchString("address.state_abbr");
    }

    public String firstName() {
        return name.firstName();
    }

    public String lastName() {
        return name.lastName();
    }

    public String latitude() {
        return String.format("%.8g", (randomService.nextDouble() * 180) - 90);
    }

    public String longitude() {
        return String.format("%.8g", (randomService.nextDouble() * 360) - 180);
    }

    public String timeZone() {
        return fakeValuesService.fetchString("address.time_zone");
    }

    public String country() { return fakeValuesService.fetchString("address.country"); }

    public String countryCode() { return fakeValuesService.fetchString("address.country_code"); }

    public String buildingNumber() {
        return fakeValuesService.numerify(fakeValuesService.fetchString("address.building_number"));
    }

    private String resolve(String key) {
        return fakeValuesService.resolve(key, this, resolver);
    }
}
