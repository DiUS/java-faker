package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testName() {
        assertThat(faker.app().name(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void testVersion() {
        assertThat(faker.app().version(), matchesRegularExpression("\\d\\.(\\d){1,2}(\\.\\d)?"));
    }

    @Test
    public void testAuthor() {
        assertThat(faker.app().author(), matchesRegularExpression("([\\w']+[-& ]?){2,9}"));
    }
}
