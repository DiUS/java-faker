package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class HitchhikersGuideToTheGalaxyTest extends AbstractFakerTest {

    @Test
    public void testCharacter() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().character().isEmpty());
    }

    @Test
    public void testLocation() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().location().isEmpty());
    }

    @Test
    public void testMarvinQuote() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().marvinQuote().isEmpty());
    }

    @Test
    public void testPlanet() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().planet().isEmpty());
    }

    @Test
    public void testQuote() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().quote().isEmpty());
    }

    @Test
    public void testSpecie() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().specie().isEmpty());
    }
}
