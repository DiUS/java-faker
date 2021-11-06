package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class AviationTest extends AbstractFakerTest {

    @Test
    public void airport() {
        assertThat(faker.aviation().airport(), matchesRegularExpression("\\w{4}"));
    }

    @Test
    public void aircraft() {
        assertThat(faker.aviation().aircraft(), isStringWithContents());
    }

    @Test
    public void metar() {
        assertThat(faker.aviation().METAR(), isStringWithContents());
    }

    @Test
    public void flight_ICAO() {
        assertThat(faker.aviation().flight("ICAO"), matchesRegularExpression("[A-Z]{3}[0-9]+"));
    }

    @Test
    public void flight_IATA() {
        assertThat(faker.aviation().flight("IATA"), matchesRegularExpression("[A-Z]{2}[0-9]+"));
    }

    @Test
    public void airline() {
        assertThat(faker.aviation().airline(), isStringWithContents());
    }
}
