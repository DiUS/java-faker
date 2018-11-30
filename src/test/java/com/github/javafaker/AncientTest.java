package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

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
