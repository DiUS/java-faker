package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.javafaker.matchers.IsANumber.isANumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class AddressTest {

    private static final Logger logger = LoggerFactory.getLogger(AddressTest.class);
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testStreetAddressIsANumber() {
        final String streetAddressNumber = faker.address().streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertThat(streetAddressNumber, isANumber());
    }

    @Test
    public void testLatitude() {
        try {
            String latStr;
            Double lat;
            for (int i = 0; i < 100; i++) {
                latStr = faker.address().latitude();
                assertNotNull(latStr);
                lat = new Double(latStr);
                assertTrue(lat >= -90 && lat <= 90);
            }
        } catch (NumberFormatException e) {
            fail("Invalid Latitude");
        }
    }

    @Test
    public void testLongitude() {
        try {
            String longStr;
            Double lon;
            for (int i=0; i<100; i++) {
                longStr = faker.address().longitude();
                assertNotNull(longStr);
                lon = new Double(longStr);
                assertTrue(lon >= -180 && lon <= 180);
            }
        } catch (NumberFormatException e) {
            fail("Invalid Longitude");
        }
    }
}
