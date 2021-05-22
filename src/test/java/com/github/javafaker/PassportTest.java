package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author CharlotteE67
 */

public class PassportTest extends AbstractFakerTest {

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testChValid() {
        assertTrue(faker.passport().chValid().matches("E[0-9A-HJ-NP-Z][0-9]{7}")
                || faker.passport().chValid().matches("G[0-9]{8}"));
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testChValidLength() {
        assertEquals(faker.passport().chValid().length(), 9);
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testChInValid() {
        assertFalse(faker.passport().chInvalid().matches("E[0-9A-HJ-NP-Z][0-9]{7}")
                && faker.passport().chInvalid().matches("G[0-9]{8}"));
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testChInValidNotNull() {
        assertNotNull(faker.passport().chInvalid());
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testAmValid() {
        assertTrue(faker.passport().amValid().matches("[0-9]{8}"));
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testAmValidLength() {
        assertEquals(faker.passport().amValid().length(), 8);
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testAmInValid() {
        assertFalse(faker.passport().amInvalid().matches("[0-9]{8}"));
    }

    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    @Test
    public void testAmInValidNotNull() {
        assertNotNull(faker.passport().amInvalid());
    }
}
