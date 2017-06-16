package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class AppTest extends AbstractFakerTest {

    @Test
    public void testName() {
        assertThat(faker.app().name(), matchesRegularExpression("([\\w-]+ ?)+"));
    }

    @Test
    public void testVersion() {
        assertThat(faker.app().version(), matchesRegularExpression("\\d\\.(\\d){1,2}(\\.\\d)?"));
    }

    @Test
    public void testAuthor() {
        assertThat(faker.app().author(), matchesRegularExpression("([\\w']+[-&,\\.]? ?){2,9}"));
    }
}
