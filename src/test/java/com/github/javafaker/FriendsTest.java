package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class FriendsTest extends AbstractFakerTest {

    @Test
    public void character() throws Exception {
        assertThat(faker.friends().character(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void location() throws Exception {
        assertThat(faker.friends().location(), matchesRegularExpression("[\\w., ]+"));
    }

    @Test
    public void quote() throws Exception {
        assertThat(faker.friends().quote(), not(isEmptyOrNullString()));
    }
}
