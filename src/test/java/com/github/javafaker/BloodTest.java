package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class BloodTest extends AbstractFakerTest{


    @Test
    public void testTypes() {
        assertThat(faker.blood().types(), matchesRegularExpression("A|B|AB|O|Rh[-+]|P1|P2|Pk1|Pk2"));
    }

    @Test
    public void testABOTypes() {
        assertThat(faker.blood().ABO_Types(), matchesRegularExpression("A|B|AB|O"));
    }

    @Test
    public void testRhTypes() {
        assertThat(faker.blood().Rh_Types(), matchesRegularExpression("Rh[-+]"));
    }

    @Test
    public void testPTypes() {
        assertThat(faker.blood().P_Types(), matchesRegularExpression("P1|P2|Pk1|Pk2"));
    }
}


