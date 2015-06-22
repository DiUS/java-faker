package com.github.javafaker.service;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Before;
import org.junit.Test;

public class CountryServiceTest {
    
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testCountryIsoHasValidLength() {
        for (int i = 0; i < 500; i++) {
            final String countryIsoValue = nextRandomCountry().iso();
            assertThat("Country Iso length", countryIsoValue.length(), is(equalTo(2)));
        }
    }

    @Test(timeout = 3000)
    public void testNameAndIsoReferToSameCountry() {
        List<String> testCountries = Arrays.asList("US", "PL", "PY", "AF", "CC");
        Country randomCountry = nextRandomCountry();
        while (!testCountries.contains(randomCountry.iso())) {
            randomCountry = nextRandomCountry();
        }

        countryNameMatchIso(randomCountry, "US", "United States");
        countryNameMatchIso(randomCountry, "PL", "Poland");
        countryNameMatchIso(randomCountry, "PY", "Paraguay");
        countryNameMatchIso(randomCountry, "AF", "Afghanistan");
        countryNameMatchIso(randomCountry, "CC", "Cocos [Keeling] Islands");
    }

    private void countryNameMatchIso(Country country, String iso, String countryName) {
        if (country.iso().equals(iso)) {
            assertThat("Country with iso code : " + iso + " should match", country.name(), is(equalTo(countryName)));
        }
    }

    private Country nextRandomCountry() {
        return faker.country();
    }
}
