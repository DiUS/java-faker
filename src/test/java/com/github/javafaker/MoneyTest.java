package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class MoneyTest extends AbstractFakerTest {

    @Test
    public void testCurrencyCode() {
        assertThat(faker.money().currencyCode(), not(isEmptyOrNullString()));
    }

    @Test
    public void testCurrencies() {
        assertThat(faker.money().currencies(), not(isEmptyOrNullString()));
    }
}
