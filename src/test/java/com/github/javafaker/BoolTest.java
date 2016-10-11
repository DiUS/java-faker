package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public class BoolTest extends AbstractFakerTest {

    @Test
    public void testBool() {
        for (int i = 0; i < 100; i++) {
            assertThat(faker.bool().bool(), isOneOf(true, false));
        }
    }
}
