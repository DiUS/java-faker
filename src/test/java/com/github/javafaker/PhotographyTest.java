package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

public class PhotographyTest extends AbstractFakerTest{

    @Test
    public void testAperture() {
        String value = faker.photography().aperture();
        assertTrue(value.startsWith("f"));
    }

    @Test
    public void testTerm() {
        String value = faker.photography().term();
        assertNonNullOrEmpty(value);
    }

    @Test
    public void brand() {
        String value = faker.photography().brand();
        assertNonNullOrEmpty(value);
    }

    @Test
    public void camera() {
        String value = faker.photography().camera();
        assertNonNullOrEmpty(value);
    }

    @Test
    public void lens() {
        String value = faker.photography().lens();
        assertNonNullOrEmpty(value);
    }

    @Test
    public void gengre() {
        String value = faker.photography().gengre();
        assertNonNullOrEmpty(value);
    }

    @Test
    public void imageTag() {
        String value = faker.photography().imageTag();
        assertNonNullOrEmpty(value);
    }

    private void assertNonNullOrEmpty(String value) {
        assertNotNull(value);
        assertFalse(value.isEmpty());
    }

    @Test
    @Repeat(times=7)
    public void shutter() {
        String value = faker.photography().shutter();
        assertThat(value, matchesRegularExpression("\\d{1,}\\/{0,1}\\d*"));
    }

    @Test
    @Repeat(times=7)
    public void iso() {
        String value = faker.photography().iso();
        assertThat(value, matchesRegularExpression("\\d{1,}"));
    }
}