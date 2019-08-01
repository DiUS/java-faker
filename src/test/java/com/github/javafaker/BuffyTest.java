package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static org.junit.Assert.assertThat;

public class BuffyTest extends AbstractFakerTest {
    @Test
    public void testCharacters() {
        assertThat(faker.buffy().characters(), isStringWithContents());
    }

    @Test
    public void testQuotes() {
        assertThat(faker.buffy().quotes(), isStringWithContents());
    }

    @Test
    public void testCelebrities() {
        assertThat(faker.buffy().celebrities(), isStringWithContents());
    }

    @Test
    public void testBigBads() {
        assertThat(faker.buffy().bigBads(), isStringWithContents());
    }

    @Test
    public void testEpisodes() {
        assertThat(faker.buffy().episodes(), isStringWithContents());
    }
}
