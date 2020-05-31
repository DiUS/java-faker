package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class BloodTest extends AbstractFakerTest{


    @Test
    public void types() {
        //System.out.println(faker.blood().types());
        assertThat(faker.blood().types(), matchesRegularExpression("A|B|AB|O|Rh+|Rh-|P1|P2|Pk1|Pk2"));
    }

    @Test
    public void ABO_Types() {
        //System.out.println(faker.blood().ABO_Types());
        assertThat(faker.blood().ABO_Types(), matchesRegularExpression("A|B|AB|O"));
    }

    @Test
    public void Rh_Types() {
        //System.out.println(faker.blood().Rh_Types());
        assertThat(faker.blood().Rh_Types(), matchesRegularExpression("Rh+|Rh-"));
    }

    @Test
    public void P_Types() {
        //System.out.println(faker.blood().P_Types());
        assertThat(faker.blood().P_Types(), matchesRegularExpression("P1|P2|Pk1|Pk2"));
    }
}


