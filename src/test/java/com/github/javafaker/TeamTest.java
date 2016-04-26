package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TeamTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testName() {
        assertThat(faker.team().name(), matchesRegularExpression("(\\w+( )?){2,4}"));
    }

    @Test
    public void testCreature() {
        assertThat(faker.team().creature(), matchesRegularExpression("\\w+( \\w+)?"));
    }

    @Test
    public void testState() {
        assertThat(faker.team().state(), matchesRegularExpression("(\\w+( )?){1,2}"));
    }

    @Test
    public void testSport() {
        assertThat(faker.team().sport(), matchesRegularExpression("\\w+"));
    }
}
