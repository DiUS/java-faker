package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class AncientTest extends AbstractFakerTest {

    @Test
    public void god() {
        assertThat(faker.ancient().god(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void primordial() {
        assertThat(faker.ancient().primordial(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void titan() {
        assertThat(faker.ancient().titan(), matchesRegularExpression("\\w+"));
    }

    @Test
    public void hero() {
        assertThat(faker.ancient().hero(), matchesRegularExpression("(?U)\\w+"));
    }

}
