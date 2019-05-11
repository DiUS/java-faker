package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

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
        assertThat(faker.harryPotter().quote(), not(is(emptyOrNullString())));
    }

    @Test
    public void book() {
        assertThat(faker.harryPotter().book(), matchesRegularExpression("Harry Potter and the ([A-Za-z'\\-]+ ?)+"));
    }
}
