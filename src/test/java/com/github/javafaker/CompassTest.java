package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompassTest extends AbstractFakerTest {
    private final static String EXPRESSION = "(north|east|west|south)+\\s{0,1}"
        + "((by|-)\\s{0,1}(north|east|west|south)+){0,1}";

    @Test
    public void testDirections() {
        assertThat(faker.compass().directions(), matchesRegularExpression(EXPRESSION));
    }
    
    @Test
    public void testAbbreviation() {
        assertThat(faker.compass().abbreviation(), 
            matchesRegularExpression("^(([NEWS]){1,3}|[NEWS]{1,2}b[NEWS]{1,2})$"));
    }
    
    @Test
    public void testAzimuth() {
        Double expected = Double.parseDouble(faker.compass().azimuth());
        Double remainer = 0.0;
        Double epilison = 1.0;
        assertEquals(expected % 22.5, remainer, epilison);
    }
    
}