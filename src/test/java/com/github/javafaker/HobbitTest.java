package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class HobbitTest extends AbstractFakerTest {

    @Test
    public void testCharacter() {
        assertFalse(faker.hobbit().character().isEmpty());
    }

    @Test
    public void testThorinsCompany() {
        assertFalse(faker.hobbit().thorinsCompany().isEmpty());
    }

    @Test
    public void testQuote() {
        assertFalse(faker.hobbit().quote().isEmpty());
    }

    @Test
    public void testLocation() {
        assertFalse(faker.hobbit().location().isEmpty());
    }
}
