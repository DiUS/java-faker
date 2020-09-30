package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class DessertTest extends AbstractFakerTest {

    @Test
    public void variety() {
        assertThat(faker.dessert().variety(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void topping() {
        assertThat(faker.dessert().topping(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void flavor() {
        assertThat(faker.dessert().flavor(), matchesRegularExpression("[A-Za-z ]+"));
    }
}
