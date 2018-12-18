package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CompassTest extends AbstractFakerTest {
    private final static String EXPRESSION = "(north|east|west|south)+\\s{0,1}"
        + "((by|-)\\s{0,1}(north|east|west|south)+){0,1}";

    @Test
    public void testDirections() {
        assertThat(faker.compass().directions(), matchesRegularExpression(EXPRESSION));
    }
    
}