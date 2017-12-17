package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class DragonBallTest extends AbstractFakerTest {

    @Test
    public void testCharacter() {
        assertFalse(faker.dragonBall().character().isEmpty());
    }
}
