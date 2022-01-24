package com.github.javafaker;

import static com.github.javafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.calculateWeight;
import static com.github.javafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.digit;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;

import com.github.javafaker.idnumbers.pt.br.DocumentFormatterUtil;
import org.hamcrest.Matcher;
import org.junit.Test;


public class CPFTest extends AbstractFakerTest {

    /**
     * A valid CPF is either a real number or a generated valid number.
     */
    @Test
    public void isValidCPF() {
        assertTrue(isCPFValid(faker.cpf().valid()));
    }

    /**
     * A invalid CPF is that dos not meet the requirements of the algorithm
     */
    @Test
    public void isInvalidCPF() {
        assertFalse(isCPFValid(faker.cpf().invalid()));
    }

    /**
     * CPF has a main format. This test validate if the number is on the correct format
     * Eg: 111.111.111-11
     */
    @Test
    public void formattedCPF() {
        final Matcher<String> cpfMatcher = matchesRegularExpression("(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)");

        assertThat(faker.cpf().valid(), cpfMatcher);
        assertThat(faker.cpf().valid(true), cpfMatcher);
        assertThat(faker.cpf().invalid(), cpfMatcher);
        assertThat(faker.cpf().invalid(true), cpfMatcher);
    }

    /**
     * Return true if the CPF is valid
     * A valid CPF is unique and have a algorithm to validate it
     * <p>
     * CPF generator could generate a valid or invalid because, somentimes, we need to test a
     * registration with invalid number
     */
    private Boolean isCPFValid(final String cpf) {
        String cpfUnmask = DocumentFormatterUtil.unmask(cpf);

        String cpfPartial = cpfUnmask.substring(0, 9);

        int d1 = digit(calculateWeight(cpfPartial, 10));
        int d2 = digit((d1 * 2) + calculateWeight(cpfPartial, 11));

        return cpfUnmask.equals((cpfPartial + d1) + d2);
    }

}
