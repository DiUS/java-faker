package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChuckNorrisTest extends AbstractFakerTest {

    @Test
    public void testFact() {
        assertThat(faker.chuckNorris().fact(), not(is(emptyOrNullString())));
    }
}
