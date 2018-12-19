package com.github.javafaker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.junit.Test;

public class StockTest extends AbstractFakerTest {

    @Test
    public void testNasdaq() {
        assertThat(faker.stock().nsdqSymbol(), not(emptyOrNullString()));
    }

    @Test
    public void testNYSE() {
        assertThat(faker.stock().nyseSymbol(), not(emptyOrNullString()));
    }

}
