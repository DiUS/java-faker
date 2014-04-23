package com.github.javafaker;

import org.junit.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class PhoneNumberTest extends AbstractFakerTest {
    public PhoneNumberTest(Locale locale, Random random) {
        super(locale, random);
    }

    @Test
    public void testPhoneNumber() {
        String phoneNumber = faker.phoneNumber().phoneNumber();
        logger.info("Phone number: " + phoneNumber);
        assertNotNull(phoneNumber);
    }
}
