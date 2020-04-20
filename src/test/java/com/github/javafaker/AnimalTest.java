package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class AnimalTest extends AbstractFakerTest {

    @Test
    public void name() {
        assertThat(faker.animal().name(), matchesRegularExpression("[A-Za-z ]+"));
    }

}
