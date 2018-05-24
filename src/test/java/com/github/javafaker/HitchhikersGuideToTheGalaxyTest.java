package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class HitchhikersGuideToTheGalaxyTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.hitchhikersGuideToTheGalaxy().character(), matchesRegularExpression("^(\\w+(\\.?\\s?'?))+$"));
    }

    @Test
    public void location() {
        assertThat(faker.hitchhikersGuideToTheGalaxy().location(), matchesRegularExpression("^(\\w+\\S?\\.?\\s?'?-?)+$"));
    }

    @Test
    public void marvinQuote() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().marvinQuote().isEmpty());
    }

    @Test
    public void planet() {
        assertThat(faker.hitchhikersGuideToTheGalaxy().planet(), matchesRegularExpression("^(\\w+-?\\s?)+$"));
    }

    @Test
    public void quote() {
        assertFalse(faker.hitchhikersGuideToTheGalaxy().quote().isEmpty());
    }

    @Test
    public void specie() {
        assertThat(faker.hitchhikersGuideToTheGalaxy().specie(), matchesRegularExpression("^(\\w+'?\\s?)+$"));
    }
}
