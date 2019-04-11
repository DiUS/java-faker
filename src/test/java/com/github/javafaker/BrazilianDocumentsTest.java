package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BrazilianDocumentsTest extends AbstractFakerTest {

    @Test
    public void cpfGeneration() {
        assertThat(faker.brazilianDocuments().cpf(true), not(isEmptyOrNullString()));
    }

    @Test
    public void cpfMatchGeneration() {
        assertThat(faker.brazilianDocuments().cpf(true), matchesRegularExpression("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}"));
    }

    @Test
    public void cpfMatchGenerationWithNoPunctuation() {
        assertThat(faker.brazilianDocuments().cpf(false), matchesRegularExpression("[0-9]{3}[0-9]{3}[0-9]{3}[0-9]{2}"));
    }

    @Test
    public void cpfIsValid() {
        assertTrue(faker.brazilianDocuments().isCPF(faker.brazilianDocuments().cpf(true)));
    }


    @Test
    public void cnpjGeneration() {
        assertThat(faker.brazilianDocuments().cnpj(true), not(isEmptyOrNullString()));
    }

    @Test
    public void cnpjMatchGenerationWithNoPunctuation() {
        assertThat(faker.brazilianDocuments().cnpj(false), matchesRegularExpression("[0-9]{2}[0-9]{3}[0-9]{3}[0-9]{4}[0-9]{2}"));
    }

    @Test
    public void cnpjMatchGeneration() {
        assertThat(faker.brazilianDocuments().cnpj(true), matchesRegularExpression("[0-9]{2}.[0-9]{3}.[0-9]{3}/[0-9]{4}-[0-9]{2}"));
    }

    @Test
    public void cnpjIsValid() {
        assertTrue(faker.brazilianDocuments().isCNPJ(faker.brazilianDocuments().cnpj(true)));
    }
}
