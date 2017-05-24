package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class JobTest extends AbstractFakerTest {
    @Test
    public void field() throws Exception {
        assertThat(faker.job().field(), matchesRegularExpression("^[A-Z][A-Za-z-]+$"));
    }

    @Test
    public void seniority() throws Exception {
        assertThat(faker.job().seniority(), matchesRegularExpression("^[A-Z][a-z]+$"));
    }

    @Test
    public void position() throws Exception {
        assertThat(faker.job().position(), matchesRegularExpression("^[A-Z][a-z]+$"));
    }

    @Test
    public void keySkills() throws Exception {
        assertThat(faker.job().keySkills(), matchesRegularExpression("([A-Za-z-]+ ?){1,3}"));
    }

    @Test
    public void title() throws Exception {
        assertThat(faker.job().title(), matchesRegularExpression("([A-Za-z-]+ ?){2,3}"));
    }
}
