package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class SizeTest extends AbstractFakerTest {

    @Test
    public void adjective(){
        assertThat(faker.size().adjective(), matchesRegularExpression("[a-zA-Z]+(-[a-zA-Z]+)?"));
    }

}
