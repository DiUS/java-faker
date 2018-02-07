package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class WeatherTest extends AbstractFakerTest {

    @Test
    public void description() {
        assertThat(faker.weather().description(), not(isEmptyOrNullString()));
    }

    @Test
    public void temperatureCelsius() {
        assertThat(faker.weather().temperatureCelsius(), matchesRegularExpression("[-]?\\d+°C"));
    }

    @Test
    public void temperatureFahrenheit() {
        assertThat(faker.weather().temperatureFahrenheit(), matchesRegularExpression("[-]?\\d+°F"));
    }
}