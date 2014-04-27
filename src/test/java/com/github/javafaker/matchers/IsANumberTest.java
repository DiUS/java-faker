package com.github.javafaker.matchers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsANumberTest {

    @Test
    public void testNumberShouldBeANumber() {
        assertThat(new IsANumber().matchesSafely("34"), is(true));
    }

    @Test
    public void testBlankIsNotANumber() {
        assertThat(new IsANumber().matchesSafely(""), is(false));
    }

    @Test
    public void testOtherCharsIsNotANumber() {
        assertThat(new IsANumber().matchesSafely("df3DF-="), is(false));
    }

    @Test
    public void testEmptyStringIsNotANumber() {
        assertThat(new IsANumber().matchesSafely("   "), is(false));
    }
}
