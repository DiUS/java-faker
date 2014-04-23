package com.github.javafaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

@RunWith(value = Parameterized.class)
public class AddressTest extends AbstractFakerTest {

    public AddressTest(Locale locale, Random random) {
        super(locale, random);
    }

    @Test
    public void testStreetName() {
        String streetName = faker.streetName();
        logger.info("Street name: " + streetName);
        assertNotNull(streetName);

        streetName = faker.address().streetName();
        logger.info("Street name: " + streetName);
        assertNotNull(streetName);
    }

    @Test
    public void testStreetAddress() {
        String streetAddress = faker.streetAddress(true);
        logger.info("Street address: " + streetAddress);
        assertNotNull(streetAddress);

        streetAddress = faker.address().streetAddress(true);
        logger.info("Street address: " + streetAddress);
        assertNotNull(streetAddress);
    }

    @Test
    public void testSecondaryAddress() {
        String secondaryAddress = faker.secondaryAddress();
        logger.info("Secondary address: " + secondaryAddress);
        assertNotNull(secondaryAddress);

        secondaryAddress = faker.address().secondaryAddress();
        logger.info("Secondary address: " + secondaryAddress);
        assertNotNull(secondaryAddress);
    }

    @Test
    public void testZipCode() {
        String zip = faker.zipCode();
        logger.info("Address zip code: " + zip);
        assertNotNull(zip);

        zip = faker.address().zipCode();
        logger.info("Address zip code: " + zip);
        assertNotNull(zip);
    }

    @Test
    public void testStreetSuffix() {
        String streetSuffix = faker.streetSuffix();
        logger.info("Street suffix: " + streetSuffix);
        assertNotNull(streetSuffix);

        streetSuffix = faker.address().streetSuffix();
        logger.info("Street suffix: " + streetSuffix);
        assertNotNull(streetSuffix);
    }

    @Test
    public void testCitySuffix() {
        String citySuffix = faker.citySuffix();
        logger.info("City suffix: " + citySuffix);
        assertNotNull(citySuffix);

        citySuffix = faker.address().citySuffix();
        logger.info("City suffix: " + citySuffix);
        assertNotNull(citySuffix);
    }

    @Test
    public void testCityPrefix() {
        String cityPrefix = faker.cityPrefix();
        logger.info("City prefix: " + cityPrefix);
        assertNotNull(cityPrefix);

        cityPrefix = faker.address().cityPrefix();
        logger.info("City prefix: " + cityPrefix);
        assertNotNull(cityPrefix);
    }

    @Test
    public void testStateAbbr() {
        String stateAbbr = faker.stateAbbr();
        logger.info("State abbr: " + stateAbbr);
        assertNotNull(stateAbbr);

        stateAbbr = faker.address().stateAbbr();
        logger.info("State abbr: " + stateAbbr);
        assertNotNull(stateAbbr);
    }

    @Test
    public void testCountry() {
        String country = faker.country();
        logger.info("Country: " + country);
        assertNotNull(country);

        country = faker.address().country();
        logger.info("Country: " + country);
        assertNotNull(country);
    }


    @Test
    public void testStreetAddressNumber() {
        String streetAddressNumber = faker.streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertNotNull(streetAddressNumber);

        streetAddressNumber = faker.streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertNotNull(streetAddressNumber);
    }
}
