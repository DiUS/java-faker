package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class HipsterTest extends AbstractFakerTest {

    @Test
    public void word() throws Exception {
        assertThat(faker.hipster().word(), matchesRegularExpression("^([\\w-+&']+ ?)+$"));
    }
}
