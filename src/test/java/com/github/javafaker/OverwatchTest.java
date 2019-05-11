package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OverwatchTest extends AbstractFakerTest {

    @Test
    public void hero() {
        assertThat(faker.overwatch().hero(), matchesRegularExpression("^(\\w+\\.?\\s?)+$"));
    }

    @Test
    public void location() {
        assertThat(faker.overwatch().location(), matchesRegularExpression("^(.+'?:?\\s?)+$"));
    }

    @Test
    public void quote() {
        assertFalse(faker.overwatch().quote().isEmpty());
    }
}
