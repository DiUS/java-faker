package com.github.javafaker;

import org.junit.Test;

import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class NameTest extends AbstractFakerTest {
    public NameTest(Locale locale, Random random) {
        super(locale, random);
    }

    @Test
    public void testNames() {
        String firstName = faker.firstName();
        logger.info("Test first name: " + firstName);
        assertNotNull(firstName);

        firstName = faker.name().firstName();
        logger.info("Test first name: " + firstName);
        assertNotNull(firstName);

        String lastName = faker.lastName();
        logger.info("Test last name: " + lastName);
        assertNotNull(lastName);

        lastName = faker.name().lastName();
        logger.info("Test last name: " + lastName);
        assertNotNull(lastName);

        String prefix = faker.prefix();
        logger.info("Test prefix: " + prefix);
        assertNotNull(prefix);

        prefix = faker.name().prefix();
        logger.info("Test prefix: " + prefix);
        assertNotNull(prefix);

        String suffix = faker.suffix();
        logger.info("Test suffix: " + suffix);
        assertNotNull(suffix);

        suffix = faker.name().suffix();
        logger.info("Test suffix: " + suffix);
        assertNotNull(suffix);

        String name = faker.name().name();
        logger.info("Test name: " + name);
        assertNotNull(name);
    }
}
