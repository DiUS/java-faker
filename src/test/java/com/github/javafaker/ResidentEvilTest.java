package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class ResidentEvilTest extends AbstractFakerTest {

    static private final String WORDS_WITH_SPECIAL_CHAR_REGEX = "^(?! )[A-Za-z0-9αγβ'.()\\- ]*(?<! )$";

    /**
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/634
     */
    @Test
    public void testCharacter() {
        String character = faker.residentEvil().character();
        assertThat(character, not(isEmptyOrNullString()));
        assertThat(character, matchesRegularExpression(WORDS_WITH_SPECIAL_CHAR_REGEX));
    }

    /**
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/634
     */
    @Test
    public void testBiologicalAgent() {
        String biologicalAgent = faker.residentEvil().biologicalAgent();
        assertThat(biologicalAgent, not(isEmptyOrNullString()));
        assertThat(biologicalAgent, matchesRegularExpression(WORDS_WITH_SPECIAL_CHAR_REGEX));
    }

    /**
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/634
     */
    @Test
    public void testEquipment() {
        String equipment = faker.residentEvil().equipment();
        assertThat(equipment, not(isEmptyOrNullString()));
        assertThat(equipment, matchesRegularExpression(WORDS_WITH_SPECIAL_CHAR_REGEX));
    }

    /**
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/634
     */
    @Test
    public void testLocation() {
        String location = faker.residentEvil().location();
        assertThat(location, not(isEmptyOrNullString()));
        assertThat(location, matchesRegularExpression(WORDS_WITH_SPECIAL_CHAR_REGEX));
    }

    /**
     * CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/634
     */
    @Test
    public void testCreature() {
        String creature = faker.residentEvil().creature();
        assertThat(creature, not(isEmptyOrNullString()));
        assertThat(creature, matchesRegularExpression(WORDS_WITH_SPECIAL_CHAR_REGEX));
    }

}
