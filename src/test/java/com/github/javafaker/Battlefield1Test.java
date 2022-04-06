package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class Battlefield1Test extends AbstractFakerTest {

    @Test
    public void classes() {
        assertThat(faker.battlefield1().classes(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }

    @Test
    public void weapon() {
        assertThat(faker.battlefield1().weapon(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }

    @Test
    public void vehicle() {
        assertThat(faker.battlefield1().vehicle(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }

    @Test
    public void map() {
        assertThat(faker.battlefield1().map(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }

    @Test
    public void faction() {
        assertThat(faker.battlefield1().faction(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }
}
