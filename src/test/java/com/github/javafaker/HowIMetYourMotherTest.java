package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class HowIMetYourMotherTest extends AbstractFakerTest {

    @Test
    public void testCharacter() {
        assertFalse(faker.howIMetYourMother().character().isEmpty());
    }

    @Test
    public void testCatchPhrase() {
        assertFalse(faker.howIMetYourMother().catchPhrase().isEmpty());
    }

    @Test
    public void testHighFive() {
        assertFalse(faker.howIMetYourMother().highFive().isEmpty());
    }

    @Test
    public void testQuote() {
        assertFalse(faker.howIMetYourMother().quote().isEmpty());
    }
}
