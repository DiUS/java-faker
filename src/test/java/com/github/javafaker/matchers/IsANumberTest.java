package com.github.javafaker.matchers;

import com.github.javafaker.DefaultLocaleRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class IsANumberTest {

    @Rule
    public DefaultLocaleRule defaultLocaleRule = DefaultLocaleRule.root();

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

    @Test
    public void testLocaleGermanyNegativeDotDoubleIsANumber() {
        defaultLocaleRule.setDefault(Locale.GERMANY);
        assertThat(new IsANumber().matchesSafely("-123.456"), is(true));
    }

    @Test
    public void testLocaleEnglishNegativeDotDoubleIsANumber() {
        defaultLocaleRule.setDefault(Locale.ENGLISH);
        assertThat(new IsANumber().matchesSafely("-123.456"), is(true));
    }

    @Test
    public void testLocaleGermanyNegativeKommaDoubleIsANumber() {
        defaultLocaleRule.setDefault(Locale.GERMANY);
        assertThat(new IsANumber().matchesSafely("-123,456"), is(true));
    }

    @Test
    public void testLocaleEnglishNegativeKommaDoubleIsANumber() {
        defaultLocaleRule.setDefault(Locale.ENGLISH);
        assertThat(new IsANumber().matchesSafely("-123,456"), is(true));
    }

    @Test
    public void testNegativeSpaceDoubleIsNotANumber() {
        defaultLocaleRule.setDefault(Locale.GERMANY);
        assertThat(new IsANumber().matchesSafely("-123 456"), is(false));
    }

    @Test
    public void testLocaleGermanyDotExponentNumberIsNotANumber() {
        defaultLocaleRule.setDefault(Locale.GERMANY);
        assertThat(new IsANumber().matchesSafely("6.02e-23"), is(false));
    }

    @Test
    public void testLocaleEnglishDotExponentNumberIsANumber() {
        defaultLocaleRule.setDefault(Locale.ENGLISH);
        assertThat(new IsANumber().matchesSafely("6.02e-23"), is(true));
    }

    @Test
    public void testLocaleGermanyKommaExponentNumberIsANumber() {
        defaultLocaleRule.setDefault(Locale.GERMANY);
        assertThat(new IsANumber().matchesSafely("6,02e-23"), is(true));
    }

    @Test
    public void testLocaleEnglishKommaExponentNumberIsNotANumber() {
        defaultLocaleRule.setDefault(Locale.ENGLISH);
        assertThat(new IsANumber().matchesSafely("6,02e-23"), is(false));
    }
}
