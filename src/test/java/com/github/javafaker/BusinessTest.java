package com.github.javafaker;

import org.junit.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class BusinessTest extends AbstractFakerTest {

    public BusinessTest(Locale locale, Random random) {
        super(locale, random);
    }

    @Test
    public void testCreditCardNumber() {
        final String creditCardNumber = faker.business().creditCardNumber();
        logger.info("Credit card number is " + creditCardNumber);
        assertNotNull(creditCardNumber);
    }

    @Test
    public void testCreditCardExpiry() {
        final String creditCardExpiry = faker.business().creditCardExpiry();
        logger.info("Credit card expiry is " + creditCardExpiry);
        assertNotNull(creditCardExpiry);
    }

    @Test
    public void testCreditCardTypes() {
        final String creditCardType = faker.business().creditCardType();
        logger.info("Credit card type is " + creditCardType);
        assertNotNull(creditCardType);
    }

}
