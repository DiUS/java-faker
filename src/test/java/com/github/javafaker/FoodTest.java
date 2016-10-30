package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class FoodTest extends AbstractFakerTest {

    @Test
    public void ingredient() {
        assertThat(faker.food().ingredient(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void spice() {
        assertThat(faker.food().spice(), matchesRegularExpression("[A-Za-z1-9- ]+"));
    }

    @Test
    public void measurement() {
        assertThat(faker.food().measurement(), matchesRegularExpression("[A-Za-z1-9/ ]+{2}"));
    }
}
