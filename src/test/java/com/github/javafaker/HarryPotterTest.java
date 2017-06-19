package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class HarryPotterTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.harryPotter().character(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }

    @Test
    public void location() {
        assertThat(faker.harryPotter().location(), matchesRegularExpression("[A-Za-z0-9'\\. &,/]+"));
    }

    @Test
    public void quote() {
        assertThat(faker.harryPotter().quote(), not(isEmptyOrNullString()));
    }

    @Test
    public void book() {
        assertThat(faker.harryPotter().book(), matchesRegularExpression("Harry Potter and the ([A-Za-z'\\-]+ ?)+"));
    }
}
