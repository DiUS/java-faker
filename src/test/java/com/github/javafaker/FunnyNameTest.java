package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class FunnyNameTest extends AbstractFakerTest {

    @Test
    public void testName() {
        assertFalse(faker.funnyName().name().isEmpty());
    }
}
