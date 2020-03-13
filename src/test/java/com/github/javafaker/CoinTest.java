package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CoinTest extends AbstractFakerTest {
// /\w+/
    @Test
    public void flip() {
        assertThat(faker.coin().flip(), matchesRegularExpression("Heads|Tails"));
    }
}
