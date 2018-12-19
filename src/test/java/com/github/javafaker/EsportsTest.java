package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class EsportsTest extends AbstractFakerTest {

    @Test
    public void player() {
        assertThat(faker.esports().player(), matchesRegularExpression("(\\w|.)+"));
    }

    @Test
    public void team() {
        assertThat(faker.esports().team(),  matchesRegularExpression("((\\w|.)+ ?)+"));
    }

    @Test
    public void event() {
        assertThat(faker.esports().event(), matchesRegularExpression("(\\w+ ?)+"));
    }

    @Test
    public void league() {
        assertThat(faker.esports().league(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void game() {
        assertThat(faker.esports().game(), matchesRegularExpression("([\\w:.]+ ?)+"));
    }
}
