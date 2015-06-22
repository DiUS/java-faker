package com.github.javafaker.service;

import com.github.javafaker.Country;
import java.util.List;

public class CountryService {

    private final RandomService randomService;
    private final List<String> isoCodes;
    private final List<String> names;

    public CountryService(FakeValuesService fakeValuesService, RandomService randomService) {
        this.randomService = randomService;
        isoCodes = (List<String>) fakeValuesService.fetchObject("country.iso");
        names = (List<String>) fakeValuesService.fetchObject("country.name");
    }
    
    public Country country() {
        int countryIndex = randomService.nextInt(isoCodes.size());
        return new Country(names.get(countryIndex), isoCodes.get(countryIndex));
    }
}
