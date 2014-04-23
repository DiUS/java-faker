package com.github.javafaker;

import org.junit.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class InternetTest extends AbstractFakerTest {

    public InternetTest(Locale locale, Random random) {
        super(locale, random);
    }

    @Test
    public void testEmailAddress() {
        String emailAddress = faker.emailAddress();
        logger.info("Test email address: " + emailAddress);
        assertNotNull(emailAddress);

        emailAddress = faker.internet().emailAddress();
        logger.info("Test email address: " + emailAddress);
        assertNotNull(emailAddress);
    }

}
