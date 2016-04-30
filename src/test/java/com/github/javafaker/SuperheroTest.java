package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class SuperheroTest {
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testName() {
        assertThat(faker.superhero().name(), matchesRegularExpression("[A-Za-z' -]+"));
    }

    @Test
    public void testPrefix() {
        assertThat(faker.superhero().prefix(), matchesRegularExpression("[A-Za-z -]+"));
    }

    @Test
    public void testSuffix() {
        assertThat(faker.superhero().suffix(), matchesRegularExpression("[A-Za-z -]+"));
    }

    @Test
    public void testPower() {
        assertThat(faker.superhero().power(), matchesRegularExpression("[A-Za-z/ -]+"));
    }

    @Test
    public void testDescriptor() {
        assertThat(faker.superhero().descriptor(), matchesRegularExpression("[A-Za-z' -]+"));
    }
}
