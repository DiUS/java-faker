package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testName() {
        assertThat(faker.company().name(), matchesRegularExpression("[A-Za-z\\-&', ]+"));
    }

    @Test
    public void testSuffix() {
        assertThat(faker.company().suffix(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void testIndustry() {
        assertThat(faker.company().industry(), matchesRegularExpression("(\\w+([ ,&/-]{1,3})?){1,4}+"));
    }

    @Test
    public void testProfession() {
        assertThat(faker.company().profession(), matchesRegularExpression("[a-z ]+"));
    }
}
