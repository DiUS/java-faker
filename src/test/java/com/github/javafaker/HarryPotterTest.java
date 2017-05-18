package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class HarryPotterTest extends AbstractFakerTest {

    @Test
    public void character() throws Exception {
        assertThat(faker.harryPotter().character(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }

    @Test
    public void location() throws Exception {
        assertThat(faker.harryPotter().location(), matchesRegularExpression("[A-Za-z0-9'\\. &,/]+"));
    }

    @Test
    public void quote() throws Exception {
        assertThat(faker.harryPotter().quote(), not(isEmptyOrNullString()));
    }

    @Test
    public void book() throws Exception {
        assertThat(faker.harryPotter().book(), matchesRegularExpression("Harry Potter and the ([A-Za-z'\\-]+ ?)+"));
    }
}
