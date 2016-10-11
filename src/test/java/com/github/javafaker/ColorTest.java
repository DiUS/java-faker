package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class ColorTest  extends AbstractFakerTest{


    @Test
    public void testName() {
        assertThat(faker.color().name(), matchesRegularExpression("(\\w+ ?){1,2}"));
    }
}
