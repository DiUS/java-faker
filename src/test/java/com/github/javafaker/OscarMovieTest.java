package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class OscarMovieTest extends AbstractFakerTest{
    @Test
    public void actor() {
        assertThat(faker.oscarMovie().actor(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }
    @Test
    public void movieName() {
        assertThat(faker.oscarMovie().movieName(), not(isEmptyOrNullString()));
    }
    @Test
    public void quote() {
        assertThat(faker.oscarMovie().quote(), not(isEmptyOrNullString()));
    }
    @Test
    public void character() {
        assertThat(faker.oscarMovie().character(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
    }
    @Test
    public void releaseDate() {
        assertThat(faker.oscarMovie().releaseDate(), matchesRegularExpression("[A-Za-z0-9\\\\,\\\\ ]+"));
    }
}
