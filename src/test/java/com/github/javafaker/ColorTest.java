package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ColorTest extends AbstractFakerTest {

    @Test
    public void testName() {
        assertThat(faker.color().name(), matchesRegularExpression("(\\w+ ?){1,2}"));
    }
}
