package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

/**
 * @author Luka Obradovic (luka@vast.com)
 */
public class LordOfTheRingsTest extends AbstractFakerTest {

    @Test
    public void character() throws Exception {
        assertThat(faker.lordOfTheRings().character(), matchesRegularExpression("(?U)([\\w ]+ ?)+"));
    }

    @Test
    public void location() throws Exception {
        assertThat(faker.lordOfTheRings().location(), matchesRegularExpression("(?U)([\\w'\\- ]+ ?)+"));
    }
}
