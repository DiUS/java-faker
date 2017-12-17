package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class StarTrekTest extends AbstractFakerTest {

    @Test
    public void testCharacter() {
        assertFalse(faker.starTrek().character().isEmpty());
    }

    @Test
    public void testLocation() {
        assertFalse(faker.starTrek().location().isEmpty());
    }

    @Test
    public void testSpecie() {
        assertFalse(faker.starTrek().specie().isEmpty());
    }

    @Test
    public void testVillain() {
        assertFalse(faker.starTrek().villain().isEmpty());
    }
}
