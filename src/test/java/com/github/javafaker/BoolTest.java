package com.github.javafaker;

import org.junit.jupiter.api.RepeatedTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;

public class BoolTest extends AbstractFakerTest {

    @RepeatedTest(100)
    public void testBool() {
        assertThat(faker.bool().bool(), is(oneOf(true, false)));
    }
}
