package com.github.javafaker;

import org.junit.Test;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

import static com.github.javafaker.matchers.IsANumber.isANumber;
import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BrazilianDocumentsTest extends AbstractFakerTest {

    @Test
    public void testCpfNumber() {
        final String cpfNumber = faker.brazilianDocuments().cpfNumber(false);
        assertThat(cpfNumber, matchesRegularExpression("[0-9]{11}"));

        final String cpfNumberFormatted = faker.brazilianDocuments().cpfNumber(true);
        assertThat(cpfNumberFormatted, matchesRegularExpression("([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})"));
    }

    @Test
    public void testCnpjNumber() {
        final String cnpjNumber = faker.brazilianDocuments().cnpjNumber(false);
        assertThat(cnpjNumber, matchesRegularExpression("[0-9]{14}"));

        final String cnpjNumberFormatted = faker.brazilianDocuments().cnpjNumber(true);
        assertThat(cnpjNumberFormatted, matchesRegularExpression("([0-9]{2})\\.([0-9]{3})\\.([0-9]{3})/([0-9]{4})\\-([0-9]{2})"));
    }

    @Test
    public void testRgNumber() {
        final String rgNumber = faker.brazilianDocuments().rgNumber(false);
        assertThat(rgNumber, matchesRegularExpression("[0-9]{9}"));

        final String rgNumberFormatted = faker.brazilianDocuments().rgNumber(true);
        assertThat(rgNumberFormatted, matchesRegularExpression("([0-9]{2})\\.([0-9]{3})\\.([0-9]{3})-([0-9])"));
    }
}
