package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isOneOf;

public class BoolTest {
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testBool() {
        assertThat(faker.bool().bool(), isOneOf(true, false));
    }
}
