package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class FriendsTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.friends().character(), matchesRegularExpression("[A-Za-z .,]+"));
    }

    @Test
    public void location() {
        assertThat(faker.friends().location(), matchesRegularExpression("[\\w.', ]+"));
    }

    @Test
    public void quote() {
        assertThat(faker.friends().quote(), not(is(emptyOrNullString())));
    }
}
