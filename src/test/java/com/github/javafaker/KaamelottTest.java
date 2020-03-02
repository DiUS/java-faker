package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class KaamelottTest extends AbstractFakerTest {

    @Test
    public void testCharacter() {
        assertThat(faker.kaamelott().character(), matchesRegularExpression("[A-Za-z' -ÇÉàçêèéïîüùú]+"));
    }

    @Test
    public void testQuote() {
        assertThat(faker.kaamelott().quote(), matchesRegularExpression("[-A-Za-z0-9 —ÇÉàçêèéïîüùú;:…\\?\\!\\.’‘'”“,\\[\\]]+"));
    }
}
