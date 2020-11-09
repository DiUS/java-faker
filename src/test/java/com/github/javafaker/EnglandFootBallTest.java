package com.github.javafaker;




import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EnglandFootBallTest extends AbstractFakerTest{

    private final String noLeadingTrailingWhitespaceRegex = "[A-Za-z ]+";

    @Test
    public void testLeague() {
        String league = faker.englandfootball().league();
        assertThat(league, not(isEmptyOrNullString()));
        assertThat(league, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testTeam() {
        String team = faker.englandfootball().team();
        assertThat(team, not(isEmptyOrNullString()));
        assertThat(team, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }
}
