package com.github.javafaker.matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

public class IsStringWithContentsTest {

    private IsStringWithContents matcher;

    @Before
    public void before() {
        matcher = new IsStringWithContents();
    }

    @Test
    public void emptyStringShouldFail() {
        assertThat(matcher.matchesSafely(""), is(false));
    }

    @Test
    public void nullStringShouldFail() {
        assertThat(matcher.matchesSafely(null), is(false));
    }

    @Test
    public void stringShouldPass() {
        assertThat(matcher.matchesSafely("123"), is(true));
    }

    @Test
    public void whiteSpaceShouldFail() {
        assertThat(matcher.matchesSafely("    "), is(false));
    }
}
