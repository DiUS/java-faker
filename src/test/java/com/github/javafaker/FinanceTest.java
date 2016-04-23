package com.github.javafaker;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FinanceTest {
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void creditCard() {
        for (int i = 0; i < 100; i++) {
            final String creditCard = faker.finance().creditCard();
            final String creditCardStripped = creditCard.replaceAll("-", "");
            assertThat(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardStripped), is(true));
        }
    }

    @Test
    public void bic() {
        assertThat(faker.finance().bic(), matchesRegularExpression("([A-Z]){4}([A-Z]){2}([0-9A-Z]){2}([0-9A-Z]{3})?"));
    }
}
