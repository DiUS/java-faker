package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class ScienceTest extends AbstractFakerTest {

    @Test
    public void element() {
        assertThat(faker.science().element(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void elementSymbol() {
        assertThat(faker.science().elementSymbol(), matchesRegularExpression("[A-Za-z]{1,2}"));
    }

    @Test
    public void scientist() {
        assertThat(faker.science().scientist(), matchesRegularExpression("[A-Za-z. ]+"));
    }

    @Test
    public void quark() {
        assertThat(faker.science().quark(), matchesRegularExpression("[A-Za-z]+"));
    }

    @Test
    public void leptons() {
        assertThat(faker.science().leptons(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void bosons() {
        assertThat(faker.science().bosons(), matchesRegularExpression("[A-Za-z ]+"));
    }
}