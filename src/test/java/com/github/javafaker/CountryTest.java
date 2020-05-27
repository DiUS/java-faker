package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class CountryTest extends AbstractFakerTest {

    @Test
    @Repeat(times=10)
    public void testFlag() {
        Country country = faker.country();
        String flag = country.flag();
        assertThat(flag, matchesRegularExpression("^http:\\/\\/flags.fmcdn\\.net\\/data\\/flags\\/w580\\/[a-zA-Z0-9_]+\\.png$"));
    }


    @Test
    @Repeat(times=10)
    public void testCode2() {
        assertThat(faker.country().countryCode2(), matchesRegularExpression("([a-z]{2})"));
    }

    @Test
    @Repeat(times=10)
    public void testCode3() {
        assertThat(faker.country().countryCode3(), matchesRegularExpression("([a-z]{3})"));
    }

    @Test
    @Repeat(times=10)
    public void testName() {
        assertThat(faker.country().name(), isStringWithContents());
    }

    @Test
    @Repeat(times=10)
    public void testCapital() {
        assertThat(faker.country().capital(), matchesRegularExpression("([\\w'-]+ ?)+"));
    }

    @Test
    @Repeat(times=10)
    public void testCurrency() {
        assertThat(faker.country().currency(), matchesRegularExpression("([A-Za-zÀ-ÿ'’()-]+ ?)+"));
    }

    @Test
    @Repeat(times=10)
    public void testCurrencyCode() {
        assertThat(faker.country().currencyCode(), matchesRegularExpression("([\\w-’í]+ ?)+"));
    }
}
