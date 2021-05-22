package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class HearthstoneTest extends AbstractFakerTest {

    @Test
    public void mainProfessionTest() {
        String profession = faker.hearthstone().mainProfession();
        assertThat(profession, matchesRegularExpression("[ A-Za-z]+"));
    }

    @Test
    public void mainCharacterTest() {
        String character = faker.hearthstone().mainCharacter();
        assertThat(character, matchesRegularExpression("[ A-Za-z]+"));
    }

    @Test
    public void mainPatternTest() {
        String pattern = faker.hearthstone().mainPattern();
        assertThat(pattern, matchesRegularExpression("[ A-Za-z]+"));
    }

    @Test
    public void battlegroundsScoreTest() {
        int score = faker.hearthstone().battlegroundsScore();
        assertTrue(score <= 16000);
        assertTrue(score >= 0);
    }

    @Test
    public void standardRankTest() {
        String rank = faker.hearthstone().standardRank();
        assertThat(rank, matchesRegularExpression("[ A-Za-z0-9]+"));
    }

    @Test
    public void wildRankTest() {
        String rank = faker.hearthstone().wildRank();
        assertThat(rank, matchesRegularExpression("[ A-Za-z0-9]+"));
    }
}
