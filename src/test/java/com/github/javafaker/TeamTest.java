package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class TeamTest extends AbstractFakerTest {

    
    @Test
    public void testName() {
        assertThat(faker.team().name(), matchesRegularExpression("(\\w+( )?){2,4}"));
    }

    @Test
    public void testCreature() {
        assertThat(faker.team().creature(), matchesRegularExpression("\\w+( \\w+)?"));
    }

    @Test
    public void testState() {
        assertThat(faker.team().state(), matchesRegularExpression("(\\w+( )?){1,2}"));
    }

    @Test
    public void testSport() {
        assertThat(faker.team().sport(), matchesRegularExpression("\\w+"));
    }
}
