package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class StarCraftTest extends AbstractFakerTest {

    private final String noLeadingTrailingWhitespaceRegex = "^(?! )[A-Za-z0-9' ]*(?<! )$";

    @Test
    public void testUnit() {
        String unit = faker.starCraft().unit();
        assertThat(unit, not(isEmptyOrNullString()));
        assertThat(unit, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testBuilding() {
        String building = faker.starCraft().building();
        assertThat(building, not(isEmptyOrNullString()));
        assertThat(building, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testCharacter() {
        String character = faker.starCraft().character();
        assertThat(character, not(isEmptyOrNullString()));
        assertThat(character, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testPlanet() {
        String planet = faker.starCraft().planet();
        assertThat(planet, not(isEmptyOrNullString()));
        assertThat(planet, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

}
