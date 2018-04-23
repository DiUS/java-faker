package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class StarTrekTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.starTrek().character(), matchesRegularExpression("^(\\w+-?'?\\.?\\s?)+$"));
    }

    @Test
    public void location() {
        assertThat(faker.starTrek().location(), matchesRegularExpression("^(\\w+'?\\s?)+$"));
    }

    @Test
    public void specie() {
        assertThat(faker.starTrek().specie(), matchesRegularExpression("^(\\w+-?'?\\s?)+$"));
    }

    @Test
    public void villain() {
        assertThat(faker.starTrek().villain(), matchesRegularExpression("^(\\w+'?\\.?\\s?)+$"));
    }
}
