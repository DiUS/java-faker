package com.github.javafaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class AddressTest extends AbstractFakerTest {

    public AddressTest(Locale locale, Random random) {
        super(locale, random);
    }


    private static final String[] METHODS = new String[]{"streetName",
            "secondaryAddress",
            "zipCode",
            "streetSuffix",
            "citySuffix",
            "cityPrefix",
            "stateAbbr",
            "zipCode",
            "country",
            "streetAddressNumber"};

    @Test
    public void testDataReturnedIsNotNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String methodName : METHODS) {
            final Method method = faker.getClass().getMethod(methodName);
            Object value = method.invoke(faker);

            logger.info(String.format("%s invoked returns %s", methodName, value));
            assertNotNull(String.format("faker.%s returned null", methodName), value);
        }
    }

    @Test
    public void testDataFromAddressIsNotNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String methodName : METHODS) {
            final Method method = faker.address().getClass().getMethod(methodName);
            Object value = method.invoke(faker.address());

            logger.info(String.format("%s invoked returns %s", methodName, value));
            assertNotNull(String.format("faker.address().%s returned null", methodName), value);
        }
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
    public void testStreetAddressNumber() {
        String streetAddressNumber = faker.streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertTrue("street address is not a number " + streetAddressNumber, streetAddressNumber.matches("\\d+"));


        streetAddressNumber = faker.address().streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertTrue("street address is not a number " + streetAddressNumber, streetAddressNumber.matches("\\d+"));
    }
}
