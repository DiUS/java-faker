package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class UniversityTest extends AbstractFakerTest {

    @Test
    public void testName() {
        assertThat(faker.university().name(), matchesRegularExpression("[A-Za-z'() ]+"));
    }

    @Test
    public void testPrefix() {
        assertThat(faker.university().prefix(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void testSuffix() {
        assertThat(faker.university().suffix(), matchesRegularExpression("\\w+"));
    }
}
