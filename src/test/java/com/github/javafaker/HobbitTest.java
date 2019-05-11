package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
