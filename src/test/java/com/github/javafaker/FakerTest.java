package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class FakerTest extends AbstractFakerTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void bothifyShouldGenerateLettersAndNumbers() {
        assertThat(faker.bothify("????##@gmail.com"), matchesRegularExpression("\\w{4}\\d{2}@gmail.com"));
    }

    @Test
    public void letterifyShouldGenerateLetters() {
        assertThat(faker.bothify("????"), matchesRegularExpression("\\w{4}"));
    }

    @Test
    public void letterifyShouldGenerateUpperCaseLetters() {
        assertThat(faker.bothify("????",true), matchesRegularExpression("[A-Z]{4}"));
    }

    @Test
    public void letterifyShouldLeaveNonSpecialCharactersAlone() {
        assertThat(faker.bothify("ABC????DEF"), matchesRegularExpression("ABC\\w{4}DEF"));
    }

    @Test
    public void numerifyShouldGenerateNumbers() {
        assertThat(faker.numerify("####"), matchesRegularExpression("\\d{4}"));
    }

    @Test
    public void numerifyShouldLeaveNonSpecialCharactersAlone() {
        assertThat(faker.numerify("####123"), matchesRegularExpression("\\d{4}123"));
    }

    @Test
    public void regexifyShouldGenerateNumbers() {
        assertThat(faker.regexify("\\d"), matchesRegularExpression("\\d"));
    }

    @Test
    public void regexifyShouldGenerateLetters() {
        assertThat(faker.regexify("\\w"), matchesRegularExpression("\\w"));
    }

    @Test
    public void regexifyShouldGenerateAlternations() {
        assertThat(faker.regexify("(a|b)"), matchesRegularExpression("(a|b)"));
    }

    @Test
    public void regexifyShouldGenerateBasicCharacterClasses() {
        assertThat(faker.regexify("(aeiou)"), matchesRegularExpression("(aeiou)"));
    }

    @Test
    public void regexifyShouldGenerateCharacterClassRanges() {
        assertThat(faker.regexify("[a-z]"), matchesRegularExpression("[a-z]"));
    }

    @Test
    public void regexifyShouldGenerateMultipleCharacterClassRanges() {
        assertThat(faker.regexify("[a-z1-9]"), matchesRegularExpression("[a-z1-9]"));
    }

    @Test
    public void regexifyShouldGenerateSingleCharacterQuantifiers() {
        assertThat(faker.regexify("a*b+c?"), matchesRegularExpression("a*b+c?"));
    }

    @Test
    public void regexifyShouldGenerateBracketsQuantifiers() {
        assertThat(faker.regexify("a{2}"), matchesRegularExpression("a{2}"));
    }

    @Test
    public void regexifyShouldGenerateMinMaxQuantifiers() {
        assertThat(faker.regexify("a{2,3}"), matchesRegularExpression("a{2,3}"));
    }

    @Test
    public void regexifyShouldGenerateBracketsQuantifiersOnBasicCharacterClasses() {
        assertThat(faker.regexify("[aeiou]{2,3}"), matchesRegularExpression("[aeiou]{2,3}"));
    }

    @Test
    public void regexifyShouldGenerateBracketsQuantifiersOnCharacterClassRanges() {
        assertThat(faker.regexify("[a-z]{2,3}"), matchesRegularExpression("[a-z]{2,3}"));
    }

    @Test
    public void regexifyShouldGenerateBracketsQuantifiersOnAlternations() {
        assertThat(faker.regexify("(a|b){2,3}"), matchesRegularExpression("(a|b){2,3}"));
    }

    @Test
    public void regexifyShouldGenerateEscapedCharacters() {
        assertThat(faker.regexify("\\.\\*\\?\\+"), matchesRegularExpression("\\.\\*\\?\\+"));
    }

    @Test
    public void regexifyShouldGenerateSameValueForFakerWithSameSeed() {
        long seed = 1L;
        String regex = "\\d";

        String firstResult = new Faker(new Random(seed)).regexify(regex);
        String secondResult = new Faker(new Random(seed)).regexify(regex);

        assertThat(secondResult, is(firstResult));
    }

    @Test
    public void resolveShouldReturnValueThatExists() {
        assertThat(faker.resolve("address.city_prefix"), not(isEmptyString()));
    }

    @Test(expected = RuntimeException.class)
    public void resolveShouldThrowExceptionWhenPropertyDoesntExist() {
        final String resolve = faker.resolve("address.nothing");
        assertThat(resolve, is(nullValue()));
    }
}
