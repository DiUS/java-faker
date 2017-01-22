package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GameOfThronesTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.gameOfThrones().character(), matchesRegularExpression("[A-Za-z'\\-\\(\\) ]+"));
    }

    @Test
    public void house() {
        assertThat(faker.gameOfThrones().house(), matchesRegularExpression("[A-Za-z' ]+"));
    }

    @Test
    public void city() {
        assertThat(faker.gameOfThrones().city(), matchesRegularExpression("[A-Za-z' ]+"));
    }

    @Test
    public void dragon() {
        assertThat(faker.gameOfThrones().dragon(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void quote() {
        assertThat(faker.gameOfThrones().quote(), not(isEmptyOrNullString()));
    }
}
