package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class FoodTest extends AbstractFakerTest {

    @Test
    public void ingredient() {
        assertThat(faker.food().ingredient(), matchesRegularExpression("[A-Za-z- ]+"));
    }

    @Test
    public void spice() {
        assertThat(faker.food().spice(), matchesRegularExpression("[A-Za-z1-9- ]+"));
    }

    @Test
    public void dish() {
        assertThat(faker.food().dish(), matchesRegularExpression("\\P{Cc}+"));
    }

    @Test
    public void fruit() {
        assertThat(faker.food().fruit(), matchesRegularExpression("[A-Za-z1-9- ]+"));
    }

    @Test
    public void vegetable() {
        assertThat(faker.food().vegetable(), matchesRegularExpression("[A-Za-z1-9- ]+"));
    }

    @Test
    public void sushi() {
        assertThat(faker.food().sushi(), matchesRegularExpression("[A-Za-z1-9- ]+"));
    }

    @Test
    public void measurement() {
        assertThat(faker.food().measurement(), matchesRegularExpression("[A-Za-z1-9/ ]+{2}"));
    }
}
