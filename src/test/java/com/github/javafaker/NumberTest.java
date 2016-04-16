package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import java.lang.*;
import java.lang.Number;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class NumberTest {

    private Faker faker;

    @Before
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testRandomDigit() {
        for (int i = 0; i < 1000; ++i) {
            int value = faker.number().randomDigit();
            assertTrue(value <= 9 && value >= 0);
        }
    }

    @Test
    public void testRandomDigitNotNull() {
        for (int i = 0; i < 1000; ++i) {
            int value = faker.number().randomDigitNotNull();
            assertTrue(value <= 9 && value > 0);
        }
    }

    @Test
    public void testRandomNumber() {
        long value = faker.number().randomNumber();
        assertTrue(value < Long.MAX_VALUE);
    }

    @Test
    public void testRandomNumberWithSingleDigitStrict() {
        for (int i = 0; i < 100; ++i) {
            long value = faker.number().randomNumber(1, true);
            assertTrue(value < 10 && value >= 0);
        }
    }

    @Test
    public void testRandomNumberWithGivenDigitsStrict() {
        for (int i = 1; i < 9; ++i) {
            for (int x = 0; x < 100; ++x) {
                long value = faker.number().randomNumber(i, true);
                String stringValue = String.valueOf(value);
                assertEquals(i, stringValue.length());
            }
        }
    }

    @Test
    public void testRandomDouble() {
        for (int i = 1; i < 5; ++i) {
            for (int x = 0; x < 100; ++x) {
                double value = faker.number().randomDouble(i, 1, 1000);
                String strVal = BigDecimal.valueOf(value).stripTrailingZeros().toString();
                if (strVal.contains(".") && !strVal.contains("+")) {
                    int ind = strVal.indexOf(".");
                    assertTrue(i >= strVal.length() - ind - 1);
                }
            }
        }
    }

    @Test
    public void testNumberBetween() {
        for (int i = 1; i < 100; ++i) {
            int v = faker.number().numberBetween(0, i);
            assertTrue(v <= i && v >= 0);
        }

        for (long i = 1L; i < 100L; ++i) {
            long v = faker.number().numberBetween(0, i);
            assertTrue(v <= i && v >= 0);
        }

        int min1 = 1;
        long v1 = faker.number().numberBetween(min1, 980000000L);
        assertTrue(v1 > min1 && v1 < 980000000L);
    }
}
