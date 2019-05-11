package com.github.javafaker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountryTest extends AbstractFakerTest {

    @RepeatedTest(10)
    public void testFlag() {
        String flag = faker.country().flag();
        assertThat(flag, matchesRegularExpression("^http:\\/\\/flags.fmcdn\\.net\\/data\\/flags\\/w580\\/[a-zA-Z0-9_]+\\.png$"));
    }

    @Test
    public void testCode2() {
        assertThat(faker.country().countryCode2(), matchesRegularExpression("([a-z]{2})"));
    }

    @Test
    public void testCode3() {
        assertThat(faker.country().countryCode3(), matchesRegularExpression("([a-z]{3})"));
    }

    @RepeatedTest(10)
    public void testCapital() {
        assertThat(faker.country().capital(), matchesRegularExpression("([\\w'-ú]+ ?)+"));
    }

    @Test
    public void testCurrency() {
        assertThat(faker.country().currency(), matchesRegularExpression("([\\w-]+ ?)+"));
    }

    @Test
    public void testCurrencyCode() {
        assertThat(faker.country().currencyCode(), matchesRegularExpression("([\\w-]+ ?)+"));
    }
}
