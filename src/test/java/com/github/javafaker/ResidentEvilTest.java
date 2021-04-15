package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ResidentEvilTest extends AbstractFakerTest {

    private final String noLeadingTrailingWhitespaceRegex = "^(?! )[A-Za-z0-9αγβ'.()\\- ]*(?<! )$";

    @Test
    public void testCharacter() {
        String character = faker.residentEvil().character();
        assertThat(character, not(isEmptyOrNullString()));
        assertThat(character, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testBiologicalAgent() {
        String biologicalAgent = faker.residentEvil().biologicalAgent();
        assertThat(biologicalAgent, not(isEmptyOrNullString()));
        assertThat(biologicalAgent, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testEquipment() {
        String equipment = faker.residentEvil().equipment();
        assertThat(equipment, not(isEmptyOrNullString()));
        assertThat(equipment, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testLocation() {
        String location = faker.residentEvil().location();
        assertThat(location, not(isEmptyOrNullString()));
        assertThat(location, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

    @Test
    public void testCreature() {
        String creature = faker.residentEvil().creature();
        assertThat(creature, not(isEmptyOrNullString()));
        assertThat(creature, matchesRegularExpression(noLeadingTrailingWhitespaceRegex));
    }

}
