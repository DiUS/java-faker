package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public class OptionsTest {

    private Faker faker;
    private String[] options;

    @Before
    public void before() {
        faker = new Faker();
        options = new String[]{"A", "B", "C"};
    }

    @Test
    public void testOptionWithArray() {
        assertThat(faker.options().option(options), isOneOf(options));
    }

    @Test
    public void testOptionWithVarargs() {
        assertThat(faker.options().option("A", "B", "C"), isOneOf(options));
    }
}
