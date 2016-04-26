package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NameTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testName() {
        assertThat(faker.name().name(), matchesRegularExpression("(\\w+\\.?( )?){2,4}"));
    }

    @Test
    public void testFullName() {
        assertThat(faker.name().fullName(), matchesRegularExpression("(\\w+\\.?( )?){2,4}"));
    }

    @Test
    public void testFirstName() {
        assertThat(faker.name().firstName(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void testLastName() {
        assertThat(faker.name().lastName(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void testPrefix() {
        assertThat(faker.name().prefix(), matchesRegularExpression("\\w+\\.?"));
    }

    @Test
    public void testSuffix() {
        assertThat(faker.name().suffix(), matchesRegularExpression("\\w+"));
    }

}
