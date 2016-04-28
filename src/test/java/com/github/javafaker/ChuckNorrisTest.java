package com.github.javafaker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.junit.Before;
import org.junit.Test;

public class ChuckNorrisTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testFact() {
        assertThat(faker.chuckNorris().fact(), not(isEmptyOrNullString()));
    }
}
