package com.github.javafaker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.junit.Test;

public class ShakespeareTest  extends AbstractFakerTest{

    @Test
    public void testHamletQuote() {
        assertThat(faker.shakespeare().hamletQuote(), not(emptyOrNullString()));
    }

    @Test
    public void testAsYouLikeItQuote() {
        assertThat(faker.shakespeare().asYouLikeItQuote(), not(emptyOrNullString()));
    }

    @Test
    public void testKingRichardIIIQuote() {
        assertThat(faker.shakespeare().kingRichardIIIQuote(), not(emptyOrNullString()));
    }

    @Test
    public void testRomeoAndJulietQuote() {
        assertThat(faker.shakespeare().romeoAndJulietQuote(), not(emptyOrNullString()));
    }

}
