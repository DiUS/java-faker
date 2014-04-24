package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.javafaker.matchers.IsANumber.isANumber;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressTest {

    private static final Logger logger = LoggerFactory.getLogger(AddressTest.class);
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testStreetAddressIsANumber() {
        String streetAddressNumber = faker.streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertThat(streetAddressNumber, isANumber());

        streetAddressNumber = faker.address().streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertThat(streetAddressNumber, isANumber());
    }
}
