package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RobinTest extends AbstractFakerTest {

    @Test
    public void quote() {
        assertThat(faker.robin().quote(), matchesRegularExpression("^(\\w+\\.?-?'?\\s?)+(\\(?)?(\\w+\\s?\\.?)+(\\))?$"));
    }
}
