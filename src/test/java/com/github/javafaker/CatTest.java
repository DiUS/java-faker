package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class CatTest {
    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void name() throws Exception {
        assertThat(faker.cat().name(), matchesRegularExpression("[A-Za-z'() 0-9-]+"));
    }

    @Test
    public void breed() throws Exception {
        assertThat(faker.cat().breed(), matchesRegularExpression("[A-Za-z'() 0-9-]+"));
    }

    @Test
    public void registry() throws Exception {
        assertThat(faker.cat().registry(), matchesRegularExpression("[A-Za-z'() 0-9-]+"));
    }
}
