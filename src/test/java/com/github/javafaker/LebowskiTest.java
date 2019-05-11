package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class LebowskiTest extends AbstractFakerTest {
    @Test
    public void actor() {
        assertThat(faker.lebowski().actor(), matchesRegularExpression("^([\\w]+ ?){1,3}$"));
    }

    @Test
    public void character() {
        assertThat(faker.lebowski().character(), matchesRegularExpression("^([\\w]+ ?){1,3}$"));
    }

    @Test
    public void quote() {
        assertThat(faker.lebowski().quote(), matchesRegularExpression("^([\\w.,!?'-]+ ?)+$"));
    }
}
