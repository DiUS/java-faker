package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GenderTest extends AbstractFakerTest {

    @Test
    public void testType() {
        assertThat(faker.gender().type(), matchesRegularExpression("[\\w-]+"));
    }

    @Test
    public void testBinaryType() {
        final Gender gender = faker.gender();
        assertThat(gender.binaryType(), matchesRegularExpression("Male|Female"));
    }

    @Test
    public void testShortBinaryType() {
        final Gender gender = faker.gender();
        assertThat(gender.shortBinaryType(), matchesRegularExpression("m|f"));
    }

}
