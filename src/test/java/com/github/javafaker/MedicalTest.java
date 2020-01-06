package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MedicalTest extends AbstractFakerTest {

    @Test
    public void testMedicineName() {
        assertThat(faker.medical().medicineName(), matchesRegularExpression("([\\w']+\\.?( )?){2,6}"));
    }

    @Test
    public void testDiseaseName() {
        assertThat(faker.medical().diseaseName(), matchesRegularExpression("([\\w']+\\.?( )?){2,8}"));
    }

    @Test
    public void testHospitalName() {
        assertThat(faker.medical().hospitalName(), matchesRegularExpression("[A-Z ,./&'()]+"));
    }

    @Test
    public void testSymptom() {
        assertThat(faker.medical().symptoms(), matchesRegularExpression("[\\w'\\s\\(\\)]+"));
    }


}