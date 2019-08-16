package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MilitaryTest extends AbstractFakerTest {

    @Test
    public void armyRank() {
        assertThat(faker.military().armyRank(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void marinesRank() {
        assertThat(faker.military().marinesRank(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void navyRank() {
        assertThat(faker.military().navyRank(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void airForceRank() {
        assertThat(faker.military().airForceRank(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void dodPaygrade() {
        assertThat(faker.military().dodPaygrade(), matchesRegularExpression("((E|O)\\-([0-9]|10)$)|Special"));
    }

}
