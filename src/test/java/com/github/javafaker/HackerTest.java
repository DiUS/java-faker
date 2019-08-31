package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class HackerTest extends AbstractFakerTest {

    @Test
    public void testAbbreviation() {
        assertThat(faker.hacker().abbreviation(), matchesRegularExpression("[A-Z]{2,4}"));
    }

    @Test
    public void testAdjective() {
        assertThat(faker.hacker().adjective(), matchesRegularExpression("(\\w+[- ]?){1,2}"));
    }

    @Test
    public void testNoun() {
        assertThat(faker.hacker().noun(), matchesRegularExpression("\\w+( \\w+)?"));
    }

    @Test
    public void testVerb() {
        assertThat(faker.hacker().verb(), matchesRegularExpression("\\w+( \\w+)?"));
    }

    @Test
    public void testIngverb() {
        assertThat(faker.hacker().ingverb(), matchesRegularExpression("\\w+ing( \\w+)?"));
    }
}
