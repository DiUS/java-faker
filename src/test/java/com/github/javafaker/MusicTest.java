package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MusicTest extends AbstractFakerTest {

    @Test
    public void instrument() {
        assertThat(faker.music().instrument(), matchesRegularExpression("\\w+ ?\\w+"));
    }

    @Test
    public void key() {
        assertThat(faker.music().key(), matchesRegularExpression("([A-Z])+(b|#){0,1}"));
    }

    @Test
    public void chord() {
        assertThat(faker.music().chord(), matchesRegularExpression("([A-Z])+(b|#){0,1}+(-?[a-zA-Z0-9]{0,4})"));
    }

    @Test
    public void genre() {
        assertThat(faker.music().genre(), matchesRegularExpression("[[ -]?\\w+]+"));
    }
}
