package com.github.javafaker.integration;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.Locale;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MissingFakes {

    /**
     * Added a test regarding the first names of russian that are not
     * translated. This is related to issue #250
     * 
     * Author : gandomi
     *
     */
    @Test
    public void russianFakerFirstNameShouldBeinCyrillic() {
        final Locale ru = new Locale("ru");

        Faker faker = new Faker(ru);

        String cyrillicRegEx = ".*\\p{InCyrillic}.*";

        assertThat("The russian faker first name should be in cyrillic",
                faker.name().firstName(),
                matchesRegularExpression(cyrillicRegEx));
    }

    /**
     * Added a test regarding the first names of russian that are not
     * translated. This is related to issue #250
     * 
     * Author : gandomi
     *
     */
    @Test
    public void russianFakerLastNameShouldBeinCyrillic() {
        final Locale ru = new Locale("ru");

        Faker faker = new Faker(ru);

        String cyrillicRegEx = ".*\\p{InCyrillic}.*";

        assertThat("The russian faker first name should be in cyrillic",
                faker.name().lastName(),
                matchesRegularExpression(cyrillicRegEx));
    }

}
