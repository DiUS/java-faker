package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ZeldaTest extends AbstractFakerTest {

    @Test
    public void game() {
        assertThat(faker.zelda().game(), matchesRegularExpression("[A-Za-z'\\- :]+"));
    }

    @Test
    public void character() {
        assertThat(faker.zelda().character(), matchesRegularExpression("(?U)([\\w'\\-.\\(\\)]+ ?)+"));
    }
}
