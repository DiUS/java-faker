package com.github.javafaker;

import org.junit.Test;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class BasketballTest extends AbstractFakerTest{

    @Test
    public void testCoaches() {
        assertThat(faker.basketball().coaches(), matchesRegularExpression("([\\w']+\\.?( )?){2,3}")); }

    @Test
    public void testPlayers() {
        assertThat(faker.basketball().players(), matchesRegularExpression("([\\w']+\\.?( )?-?){2,3}")); }

    @Test
    public void testPositions() {
        assertThat(faker.basketball().positions(), matchesRegularExpression("(\\w+ ?){1,2}")); }

    @Test
    public void testTeams() {
        assertThat(faker.basketball().teams(), matchesRegularExpression("([\\w']+\\.?( )?){2,3}")); }


}
