package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

public class Address {

    private final Name name;
    private final FakeValuesService fakeValuesService;

    public Address(Name name, FakeValuesService fakeValuesService) {
        this.name = name;
        this.fakeValuesService = fakeValuesService;
    }

    public String streetName() {
        return fakeValuesService.composite("address.street_name_formats",
                (String) fakeValuesService.fetchObject("address.street_name_joiner"),
                this);
    }

    public String streetAddressNumber() {
        return fakeValuesService.numerify(fakeValuesService.fetchString("address.street_address"));
    }

    public String streetAddress(boolean includeSecondary) {
        String streetAddress = fakeValuesService.composite("address.street_formats", " ", this);
        if (includeSecondary) {
            streetAddress = streetAddress + " " + secondaryAddress();
        }
        return fakeValuesService.numerify(streetAddress);
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
        return fakeValuesService.fetchString("address.city_suffix");
    }

    public String cityPrefix() {
        return fakeValuesService.fetchString("address.city_prefix");
    }

    public String stateAbbr() {
        return fakeValuesService.fetchString("address.state_abbr");
    }

    public String country() {
        return fakeValuesService.fetchString("address.country");
    }

    public String firstName() {
        return name.firstName();
    }

    public String lastName() {
        return name.lastName();
    }
}
