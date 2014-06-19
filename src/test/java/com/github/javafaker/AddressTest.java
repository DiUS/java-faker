package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.javafaker.matchers.IsANumber.isANumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
            String latStr = faker.address().latitude();
            logger.info("Latitude: {}", latStr);
            assertNotNull(latStr);
            final Double lat = new Double(latStr);
            assertTrue(lat > -90 && lat < 90);
        } catch (NumberFormatException e) {
            fail("Invalid Latitude");
        }
    }

    @Test
    public void testLongitude() {
        try {
            String longStr = faker.address().longitude();
            logger.info("Longitude: {}", longStr);
            assertNotNull(longStr);
            final Double lat = new Double(longStr);
            assertTrue(lat > -180 && lat < 180);
        } catch (NumberFormatException e) {
            fail("Invalid Longitude");
        }
    }
}
