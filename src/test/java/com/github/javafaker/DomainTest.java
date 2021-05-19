package com.github.javafaker;

import org.junit.Test;

/**
 * The type Domain test.
 * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
 */
public class DomainTest extends AbstractFakerTest {
    /**
     * Test first level domain.
     */
    @Test
    public void testFirstLvlDomain() {
        String[] components = faker.domain().firstLvlDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }

    /**
     * Test second level domain.
     */
    @Test
    public void testSecondLvlDomain() {
        String[] components = faker.domain().secondLvlDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }

    /**
     * Test full domain.
     */
    @Test
    public void testFullDomain() {
        String[] components = faker.domain().fullDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }

    /**
     * Test a random valid domain.
     */
    @Test
    public void testValidDomain() {
        String[] components = faker.domain().validDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }
}