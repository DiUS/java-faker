package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Luka Obradovic (luka@vast.com)
 */
public class LordOfTheRingsTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.lordOfTheRings().character(), matchesRegularExpression("(?U)([\\w ]+ ?)+"));
    }

    @Test
    public void location() {
        assertThat(faker.lordOfTheRings().location(), matchesRegularExpression("(?U)([\\w'\\- ]+ ?)+"));
    }
}
