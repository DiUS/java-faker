package com.github.javafaker;

import org.junit.Test;

/**
 * The type Domain test.
 * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
 */
public class DomainTest extends AbstractFakerTest {
    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testFirstLvlDomainNotNull() {
        String ret = faker.domain().firstLvlDomain("example");
        assert (ret != null);
    }

    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testFirstLvlDomain() {
        String[] components = faker.domain().firstLvlDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }

    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testSecondLvlDomainNotNull() {
        String ret = faker.domain().secondLvlDomain("example");
        assert (ret != null);
    }

    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testSecondLvlDomain() {
        String[] components = faker.domain().secondLvlDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }


    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testFullDomainNotNull() {
        String ret = faker.domain().fullDomain("example");
        assert (ret != null);
    }

    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testFullDomain() {
        String[] components = faker.domain().fullDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }

    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testValidDomainNotNull() {
        String ret = faker.domain().validDomain("example");
        assert (ret != null);
    }

    /**
     * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
     */
    @Test
    public void testValidDomain() {
        String[] components = faker.domain().validDomain("example").split("\\.");
        for (String str : components) {
            assert (str.length() > 0);
        }
    }
}