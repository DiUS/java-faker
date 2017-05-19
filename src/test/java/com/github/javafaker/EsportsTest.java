package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class EsportsTest extends AbstractFakerTest {

    @Test
    public void player() throws Exception {
        assertThat(faker.esports().player(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void team() throws Exception {
        assertThat(faker.esports().team(),  matchesRegularExpression("(\\w+ ?)+"));
    }

    @Test
    public void event() throws Exception {
        assertThat(faker.esports().event(), matchesRegularExpression("(\\w+ ?)+"));
    }

    @Test
    public void league() throws Exception {
        assertThat(faker.esports().league(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void game() throws Exception {
        assertThat(faker.esports().game(), matchesRegularExpression("([\\w:.]+ ?)+"));
    }
}
