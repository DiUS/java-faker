package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class HowIMetYourMotherTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.howIMetYourMother().character(), matchesRegularExpression("^(\\w+\\.?\\s?)+$"));
    }

    @Test
    public void catchPhrase() {
        assertFalse(faker.howIMetYourMother().catchPhrase().isEmpty());
    }

    @Test
    public void highFive() {
        assertThat(faker.howIMetYourMother().highFive(), matchesRegularExpression("^(\\w+-?\\s?)+$"));
    }

    @Test
    public void quote() {
        assertFalse(faker.howIMetYourMother().quote().isEmpty());
    }
}
