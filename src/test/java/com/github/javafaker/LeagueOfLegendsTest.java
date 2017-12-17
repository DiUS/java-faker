package com.github.javafaker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class LeagueOfLegendsTest extends AbstractFakerTest {

    @Test
    public void testChampion() {
        assertFalse(faker.leagueOfLegends().champion().isEmpty());
    }

    @Test
    public void testLocation() {
        assertFalse(faker.leagueOfLegends().location().isEmpty());
    }

    @Test
    public void testQuote() {
        assertFalse(faker.leagueOfLegends().quote().isEmpty());
    }

    @Test
    public void testSummonerSpell() {
        assertFalse(faker.leagueOfLegends().summonerSpell().isEmpty());
    }

    @Test
    public void testMasteries() {
        assertFalse(faker.leagueOfLegends().masteries().isEmpty());
    }

    @Test
    public void testRank() {
        assertFalse(faker.leagueOfLegends().rank().isEmpty());
    }
}
