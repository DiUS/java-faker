package com.github.javafaker;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;

import com.github.javafaker.shared.CPFUtils;
import org.junit.Before;
import org.junit.Test;


public class CPFTest extends AbstractFakerTest {

    private String validCPF;
    private String invalidCPF;

    @Before
    public void setup() {
        validCPF = faker.cpf().valid();
        invalidCPF = faker.cpf().invalid();
    }

    @Test
    public void isValidCPF() {
        assertTrue(isCPFValid(validCPF));
    }

    @Test
    public void isInvalidCPF() {
        assertFalse(isCPFValid(invalidCPF));
    }

    @Test
    public void formattedCPF() {
        assertThat(validCPF, matchesRegularExpression("(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)"));
    }

    private static Boolean isCPFValid(String cpf) {
        cpf = CPFUtils.unformatCPF(cpf);

        if (cpf.length() != 11)
            return false;
        String numDig = cpf.substring(0, 9);
        return CPFUtils.calculateVerificationDigit(numDig).equals(cpf.substring(9, 11));
    }
}
