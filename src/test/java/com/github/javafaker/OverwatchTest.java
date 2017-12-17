package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class OverwatchTest extends AbstractFakerTest {

    @Test
    public void testHero() {
        assertFalse(faker.overwatch().hero().isEmpty());
    }

    @Test
    public void testLocation() {
        assertFalse(faker.overwatch().location().isEmpty());
    }

    @Test
    public void testQuote() {
        assertFalse(faker.overwatch().quote().isEmpty());
    }
}
