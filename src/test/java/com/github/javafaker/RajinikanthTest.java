package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class RajinikanthTest extends AbstractFakerTest {

    @Test
    public void testFact() {
        assertThat(faker.rajinikanth().fact(), not(isEmptyOrNullString()));
    }
}
