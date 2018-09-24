package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MedicalTest extends AbstractFakerTest {

    @Test
    public void testMedicineName() {
        assertThat(faker.medical().medicineName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
    }
    @Test
    public void testDiseaseName() {
        assertThat(faker.medical().diseaseName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
    }
    @Test
    public void testHospitalName() {
        assertThat(faker.medical().hospitalName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
    }
    @Test
    public void testSymptom() {
        assertThat(faker.medical().symptoms(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
    }

}
