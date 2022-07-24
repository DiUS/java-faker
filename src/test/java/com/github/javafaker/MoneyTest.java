package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class MoneyTest extends AbstractFakerTest {

    @Test
    public void testCurrency() {
        assertThat(faker.money().currency(), not(isEmptyOrNullString()));
    }

    @Test
    public void testCurrencyCode() {
        assertThat(faker.money().currencyCode(), not(isEmptyOrNullString()));
    }
}
