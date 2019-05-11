package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class HipsterTest extends AbstractFakerTest {

    @Test
    public void word() {
        assertThat(faker.hipster().word(), matchesRegularExpression("^([\\w-+&']+ ?)+$"));
    }
}
