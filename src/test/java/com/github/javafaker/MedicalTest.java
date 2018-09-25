package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class MedicalTest extends AbstractFakerTest {

    @Test
    public void testMedicineName() {
        assertThat(faker.medical().medicineName(), matchesRegularExpression("([\\w']+\\.?( )?){2,5}"));
    }

    @Test
    public void testDiseaseName() {
        assertThat(faker.medical().diseaseName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
    }

    @Test
    public void testHospitalName() {
        assertThat(faker.medical().hospitalName(), matchesRegularExpression("([\\w']+\\.?( )?){2,8}"));
    }

    @Test
    public void testSymptom() {
        assertThat(faker.medical().symptoms(), matchesRegularExpression("([\\w']+\\.?.\\(?[\\w']\\)?( )?){2,4}"));
    }


    @Test
    public void testSymptomStatic() {
        assertThat("Infertility (Female)", matchesRegularExpression("([\\w']+\\.?.\\(?[\\w']\\)?( )?){2,4}"));
    }

    @Test
    public void testHospitalStatic() {
        assertThat("IREDELL MEMORIAL HOSPITAL, INCORPORATED", matchesRegularExpression("([\\w',]+\\.?.\\(?[\\w']\\)?( )?){2,4}"));
    }

}