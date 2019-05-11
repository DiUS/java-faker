package com.github.javafaker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class StockTest extends AbstractFakerTest {

    @Test
    public void testNasdaq() {
        assertThat(faker.stock().nsdqSymbol(), not(is(emptyOrNullString())));
    }

    @Test
    public void testNYSE() {
        assertThat(faker.stock().nyseSymbol(), not(is(emptyOrNullString())));
    }

}
