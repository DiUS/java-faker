package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class NationTest extends AbstractFakerTest {

    @Test
    public void spice() {
        assertThat(faker.nation().spice(), matchesRegularExpression("[A-Za-z1-9- ]+"));
    }

}
