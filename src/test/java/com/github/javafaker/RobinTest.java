package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class RobinTest extends AbstractFakerTest {

    @Test
    public void testQuote() {
        assertFalse(faker.robin().quote().isEmpty());
    }
}
