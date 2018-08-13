package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class DogTest extends AbstractFakerTest {

    @Test
    public void name() {
        assertThat(faker.dog().name(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void breed() {
        assertThat(faker.dog().breed(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void sound() {
        assertThat(faker.dog().sound(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void memePhrase() {
        assertThat(faker.dog().memePhrase(), matchesRegularExpression("[A-Za-z0-9'\\/ ]+"));
    }

    @Test
    public void age() {
        assertThat(faker.dog().age(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void gender() {
        assertThat(faker.dog().gender(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void coatLength() {
        assertThat(faker.dog().coatLength(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void size() {
        assertThat(faker.dog().size(), matchesRegularExpression("[A-Za-z ]+"));
    }
}
