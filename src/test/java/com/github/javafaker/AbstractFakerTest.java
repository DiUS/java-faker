package com.github.javafaker;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Random;

@RunWith(value = Parameterized.class)
public abstract class AbstractFakerTest {

    private static final Locale FINISH_LOCALE = new Locale("fi", "FI");
    protected static final Logger logger = LoggerFactory.getLogger(AbstractFakerTest.class);
    protected Faker faker;

    public AbstractFakerTest(Locale locale, Random random) {
        if (locale != null && random != null) {
            faker = new Faker(locale, random);
        } else if (locale != null) {
            faker = new Faker(locale);
        } else if (random != null) {
            faker = new Faker(random);
        } else {
            faker = new Faker();
        }
    }

    @Parameterized.Parameters(name = "testing locale {0} and random {1}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {Locale.ENGLISH, null},
                {Locale.FRENCH, null},
                {FINISH_LOCALE, null},
                {Locale.ENGLISH, new Random()},
                {null, new Random()},
                {null, null}};
        return Arrays.asList(data);
    }
}
