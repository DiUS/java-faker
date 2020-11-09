package com.github.javafaker;



import com.github.javafaker.matchers.MatchesRegularExpression;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EnglandFootBallTest extends AbstractFakerTest{

    private final String noLeadingTrailingWhitespaceRegex = "^(?! )[A-Za-z0-9 ]*(?<! )$";

    @Test
    public void testLeague() {
        String league = faker.englandfootball().league();
        assertThat(league, not(isEmptyOrNullString()));
        assertThat(league, MatchesRegularExpression.matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testTeam() {
        String team = faker.englandfootball().team();
        assertThat(team, not(isEmptyOrNullString()));
        assertThat(team, MatchesRegularExpression.matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }
}
