package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

public class DiseaseTest extends AbstractFakerTest {
    @Test
    public void testInternalDisease() {
        Faker faker = new Faker();
        assertThat(faker.disease().internalDisease(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testNeurology() {
        Faker faker = new Faker();
        assertThat(faker.disease().neurology(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testSurgery() {
        Faker faker = new Faker();
        assertThat(faker.disease().surgery(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testPaediatrics() {
        Faker faker = new Faker();
        assertThat(faker.disease().paediatrics(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testGynecologyAndObstetrics() {
        Faker faker = new Faker();
        assertThat(faker.disease().gynecologyAndObstetrics(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testOphthalmologyAndOtorhinolaryngology() {
        Faker faker = new Faker();
        assertThat(faker.disease().ophthalmologyAndOtorhinolaryngology(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testDermatolory() {
        Faker faker = new Faker();
        assertThat(faker.disease().dermatolory(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }


    @Test
    public void testInternalDiseaseWith10000Times() {
        Faker faker = new Faker();
        boolean isExist = false;
        for (int i = 0; i < 10000; i++) {
            String generateString = faker.disease().internalDisease();
            if (generateString.equals("anaphylaxis")) {
                isExist = true;
            }
        }
        assertTrue(isExist);
    }

    @Test
    @Repeat(times = 10000)
    public void testNeurologyWith10000Times() {
        Faker faker = new Faker();
        assertThat(faker.disease().neurology(), isStringWithContents());
    }

    @Test
    @Repeat(times = 10000)
    public void testSurgeryWith10000Times() {
        Faker faker = new Faker();
        assertThat(faker.disease().surgery(), isStringWithContents());
    }

    @Test
    @Repeat(times = 10000)
    public void testPaediatricsWith10000Times() {
        Faker faker = new Faker();
        assertThat(faker.disease().paediatrics(), isStringWithContents());
    }

    @Test
    @Repeat(times = 10000)
    public void testGynecologyAndObstetricsWith10000Times() {
        Faker faker = new Faker();
        assertThat(faker.disease().gynecologyAndObstetrics(), isStringWithContents());
    }

    @Test
    @Repeat(times = 10000)
    public void testOphthalmologyAndOtorhinolaryngologyWith10000Times() {
        Faker faker = new Faker();
        assertThat(faker.disease().ophthalmologyAndOtorhinolaryngology(), isStringWithContents());
    }

    @Test
    @Repeat(times = 10000)
    public void testDermatoloryWith10000Times() {
        Faker faker = new Faker();
        assertThat(faker.disease().dermatolory(), isStringWithContents());
    }

}
