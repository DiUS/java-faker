package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class JobTest extends AbstractFakerTest {

    @Test
    public void field() {
        assertThat(faker.job().field(), matchesRegularExpression("^[A-Z][A-Za-z-]+$"));
    }

    @Test
    public void seniority() {
        assertThat(faker.job().seniority(), matchesRegularExpression("^[A-Z][a-z]+$"));
    }

    @Test
    public void position() {
        assertThat(faker.job().position(), matchesRegularExpression("^[A-Z][a-z]+$"));
    }

    @Test
    public void keySkills() {
        assertThat(faker.job().keySkills(), matchesRegularExpression("([A-Za-z-]+ ?){1,3}"));
    }

    @Test
    public void title() {
        assertThat(faker.job().title(), matchesRegularExpression("([A-Za-z-]+ ?){2,3}"));
    }
}
