package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class WeatherTest extends AbstractFakerTest {

    @Test
    public void description() {
        assertThat(faker.weather().description(), not(isEmptyOrNullString()));
    }
}