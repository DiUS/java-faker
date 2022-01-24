package com.github.javafaker;

import com.github.javafaker.idnumbers.pt.br.DocumentFormatterUtil;
import org.hamcrest.Matcher;
import org.junit.Test;

import static com.github.javafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.calculateWeight;
import static com.github.javafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil.digit;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class CNPJTest extends AbstractFakerTest {

    /**
     * A valid CNPJ is either a real number or a generated valid number.
     */
    @Test
    public void isValidCNPJ() {
        assertTrue(isCNPJValid(faker.cnpj().valid()));
    }

    /**
     * A invalid CNPJ is that dos not meet the requirements of the algorithm
     */
    @Test
    public void isInvalidCNPJ() {
        assertFalse(isCNPJValid(faker.cnpj().invalid()));
    }

    /**
     * CNPJ has a main format. This test validate if the number is on the correct format
     * Eg: 11.111.111/0001-11
     */
    @Test
    public void formattedCNPJ() {
        final Matcher<String> cnpjMatcher = matchesRegularExpression("(^\\d{2}\\x2E\\d{3}\\x2E\\d{3}\\x2F\\d{4}\\x2D\\d{2}$)");

        assertThat(faker.cnpj().valid(), cnpjMatcher);
        assertThat(faker.cnpj().valid(true), cnpjMatcher);
        assertThat(faker.cnpj().invalid(), cnpjMatcher);
        assertThat(faker.cnpj().invalid(true), cnpjMatcher);
    }

    /**
     * Return true if the CNPJ is valid
     * A valid CNPJ is unique and have a algorithm to validate it
     * <p>
     * CNPJ generator could generate a valid or invalid because, somentimes, we need to test a
     * registration with invalid number
     */
    private Boolean isCNPJValid(final String cnpj) {
        String cnpjUnmask = DocumentFormatterUtil.unmask(cnpj);

        String cnpjPartial = cnpjUnmask.substring(0, 12);

        int d1 = digit(calculateWeight(cnpjPartial.substring(4, 12), 9) + calculateWeight(cnpjPartial.substring(0, 4), 5));
        int d2 = digit((d1 * 2) + calculateWeight(cnpjPartial.substring(5, 12), 9) + calculateWeight(cnpjPartial.substring(0, 5), 6));

        return cnpjUnmask.equals((cnpjPartial + d1) + d2);
    }

}
