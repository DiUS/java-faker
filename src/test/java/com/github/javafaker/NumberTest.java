package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.*;
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
            assertThat(value, is(lessThanOrEqualTo(9)));
            assertThat(value, is(greaterThanOrEqualTo(0)));
        }
    }

    @Test
    public void testRandomDigitNotZero() {
        for (int i = 0; i < 1000; ++i) {
            int value = faker.number().randomDigitNotZero();
            assertThat(value, is(lessThanOrEqualTo(9)));
            assertThat(value, is(greaterThan(0)));
        }
    }

    @Test
    public void testRandomNumber() {
        long value = faker.number().randomNumber();
        assertThat(value, is(lessThan(Long.MAX_VALUE)));
    }

    @Test
    public void testRandomNumberWithSingleDigitStrict() {
        for (int i = 0; i < 100; ++i) {
            long value = faker.number().randomNumber(1, true);
            assertThat(value, is(lessThan(10L)));
            assertThat(value, is(greaterThanOrEqualTo(0L)));
        }
    }

    @Test
    public void testRandomNumberWithGivenDigitsStrict() {
        for (int i = 1; i < 9; ++i) {
            for (int x = 0; x < 100; ++x) {
                long value = faker.number().randomNumber(i, true);
                String stringValue = String.valueOf(value);
                assertThat(stringValue.length(), is(i));
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
                    assertThat(strVal.length() - ind - 1, is(lessThanOrEqualTo(i)));
                }
            }
        }
    }

    @Test
    public void testNumberBetween() {
        for (int i = 1; i < 100; ++i) {
            int v = faker.number().numberBetween(0, i);
            assertThat(v, is(lessThanOrEqualTo(i)));
            assertThat(v, is(greaterThanOrEqualTo(0)));
        }

        for (long i = 1L; i < 100L; ++i) {
            long v = faker.number().numberBetween(0, i);
            assertThat(v, is(lessThanOrEqualTo(i)));
            assertThat(v, is(greaterThanOrEqualTo(0L)));
        }

        int min1 = 1;
        long v1 = faker.number().numberBetween(min1, 980000000L);
        assertThat(v1, is(greaterThan((long) min1)));
        assertThat(v1, is(lessThan(980000000L)));
    }
}
