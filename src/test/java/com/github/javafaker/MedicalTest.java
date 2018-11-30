package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class MedicalTest extends AbstractFakerTest {

    @Test
    public void testMedicineName() {
        assertThat(faker.medical().medicineName(), matchesRegularExpression("([\\w']+\\.?( )?){2,5}"));
    }

    @Test
    public void testDiseaseName() {
        assertThat(faker.medical().diseaseName(), matchesRegularExpression("([\\w']+\\.?( )?){2,8}"));
    }

    @Test
    public void testHospitalName() {
        assertThat(faker.medical().hospitalName(), matchesRegularExpression("([\\w',]+\\.?.\\(?[\\w']\\)?( )?){2,10}"));
    }

    @Test
    public void testSymptom() {
        assertThat(faker.medical().symptoms(), matchesRegularExpression("([\\w']+\\.?.\\(?[\\w']\\)?( )?){2,5}"));
    }


}