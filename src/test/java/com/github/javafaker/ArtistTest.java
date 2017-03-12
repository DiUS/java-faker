package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class ArtistTest extends AbstractFakerTest {

    @Test
    public void name() {
        assertThat(faker.artist().name(), matchesRegularExpression("(\\w+ ?){1,2}"));
    }
}
