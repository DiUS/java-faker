package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MoodTest extends AbstractFakerTest {

    @Test
    public void feeling(){
        assertThat(faker.mood().feeling(), matchesRegularExpression("[a-zA-Z]+"));
    }

    @Test
    public void emotion(){
        assertThat(faker.mood().emotion(), matchesRegularExpression("[a-zA-Z]+"));
    }

    @Test
    public void tone(){
        assertThat(faker.mood().tone(), matchesRegularExpression("[a-zA-Z]+"));
    }

}
