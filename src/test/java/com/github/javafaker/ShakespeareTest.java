package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShakespeareTest  extends AbstractFakerTest{

    @Test
    public void testHamletQuote() {
        assertThat(faker.shakespeare().hamletQuote(), not(is(emptyOrNullString())));
    }

    @Test
    public void testAsYouLikeItQuote() {
        assertThat(faker.shakespeare().asYouLikeItQuote(), not(is(emptyOrNullString())));
    }

    @Test
    public void testKingRichardIIIQuote() {
        assertThat(faker.shakespeare().kingRichardIIIQuote(), not(is(emptyOrNullString())));
    }

    @Test
    public void testRomeoAndJulietQuote() {
        assertThat(faker.shakespeare().romeoAndJulietQuote(), not(is(emptyOrNullString())));
    }

}
