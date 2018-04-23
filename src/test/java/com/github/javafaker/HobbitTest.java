package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class HobbitTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.hobbit().character(), matchesRegularExpression("^(\\(?\\w+\\.?\\s?\\)?)+$"));
    }

    @Test
    public void thorinsCompany() {
        assertThat(faker.hobbit().thorinsCompany(), matchesRegularExpression("^(\\w+\\s?)+$"));
    }

    @Test
    public void quote() {
        assertFalse(faker.hobbit().quote().isEmpty());
    }

    @Test
    public void location() {
        assertThat(faker.hobbit().location(), matchesRegularExpression("^(\\w+'?-?\\s?)+$"));
    }
}
