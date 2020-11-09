package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class GenderTest extends AbstractFakerTest {

    @Test
    public void types() {
        assertThat(faker.gender().types(), matchesRegularExpression("(\\w+ ?){1,2}"));
    }

    @Test
    public void binaryTypes() {
        assertThat(faker.gender().binaryTypes(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void shortBinaryTypes() {
        assertThat(faker.gender().shortBinaryTypes(), matchesRegularExpression("f|m"));
    }

}
