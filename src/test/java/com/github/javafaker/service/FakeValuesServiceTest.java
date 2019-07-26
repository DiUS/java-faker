package com.github.javafaker.service;

import com.github.javafaker.AbstractFakerTest;
import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class FakeValuesServiceTest extends AbstractFakerTest {

    private static final Long MILLIS_IN_AN_HOUR = 1000 * 60 * 60L;
    private static final Long MILLIS_IN_A_DAY = MILLIS_IN_AN_HOUR * 24;

    @Mock
    private RandomService randomService;

    private FakeValuesService fakeValuesService;

    @Before
    public void before() {
        super.before();
        MockitoAnnotations.initMocks(this);

        // always return the first element
        when(randomService.nextInt(anyInt())).thenReturn(0);

        fakeValuesService = spy(new FakeValuesService(new Locale("test"), randomService));
    }

    @Test
    public void fetchStringShouldReturnValue() {
        assertThat(fakeValuesService.fetchString("property.dummy"), is("x"));
    }

    @Test
    public void fetchShouldReturnValue() {
        assertThat(fakeValuesService.fetch("property.dummy"), Is.<Object>is("x"));
    }

    @Test
    public void fetchObjectShouldReturnValue() {
        assertThat(fakeValuesService.fetchObject("property.dummy"), Is.<Object>is(Arrays.asList("x", "y", "z")));
    }

    @Test
    public void safeFetchShouldReturnValueInList() {
        doReturn(0).when(randomService).nextInt(Mockito.anyInt());
        assertThat(fakeValuesService.safeFetch("property.dummy", null), is("x"));
    }

    @Test
    public void safeFetchShouldReturnSimpleList() {
        assertThat(fakeValuesService.safeFetch("property.simple", null), is("hello"));
    }

    @Test
    public void safeFetchShouldReturnEmptyStringWhenPropertyDoesntExist() {
        assertThat(fakeValuesService.safeFetch("property.dummy2", ""), isEmptyString());
    }

    @Test
    public void bothify2Args() {
        final DummyService dummy = mock(DummyService.class);

        Faker f = new Faker();

        String value = fakeValuesService.resolve("property.bothify_2", dummy, f);
        assertThat(value, matchesRegularExpression("[A-Z]{2}\\d{2}"));
    }

    @Test
    public void regexifyDirective() {
        final DummyService dummy = mock(DummyService.class);

        String value = fakeValuesService.resolve("property.regexify1", dummy, faker);
        assertThat(value, isOneOf("55", "44", "45", "54"));
        verify(faker).regexify("[45]{2}");
    }

    @Test
    public void regexifySlashFormatDirective() {
        final DummyService dummy = mock(DummyService.class);

        String value = fakeValuesService.resolve("property.regexify_slash_format", dummy, faker);
        assertThat(value, isOneOf("55", "44", "45", "54"));
        verify(faker).regexify("[45]{2}");
    }

    @Test
    public void regexifyDirective2() {
        final DummyService dummy = mock(DummyService.class);

        String value = fakeValuesService.resolve("property.regexify_cell", dummy, faker);
        assertThat(value, isOneOf("479", "459"));
        verify(faker).regexify("4[57]9");
    }

    @Test
    public void resolveKeyToPropertyWithAPropertyWithoutAnObject() {
        // #{hello} -> DummyService.hello

        // given
        final DummyService dummy = mock(DummyService.class);
        doReturn("Yo!").when(dummy).hello();

        // when
        final String actual = fakeValuesService.resolve("property.simpleResolution", dummy, faker);

        // then
        assertThat(actual, is("Yo!"));
        verify(dummy).hello();
        verifyZeroInteractions(faker);
    }

    @Test
    public void resolveKeyToPropertyWithAPropertyWithAnObject() {
        // given
        final Superhero person = mock(Superhero.class);
        final DummyService dummy = mock(DummyService.class);
        doReturn(person).when(faker).superhero();
        doReturn("Luke Cage").when(person).name();

        // when
        final String actual = fakeValuesService.resolve("property.advancedResolution", dummy, faker);

        // then
        assertThat(actual, is("Luke Cage"));
        verify(faker).superhero();
        verify(person).name();
    }

    @Test
    public void resolveKeyToPropertyWithAList() {
        // property.resolutionWithList -> #{hello}
        // #{hello} -> DummyService.hello

        // given
        final DummyService dummy = mock(DummyService.class);
        doReturn(0).when(randomService).nextInt(Mockito.anyInt());
        doReturn("Yo!").when(dummy).hello();

        // when
        final String actual = fakeValuesService.resolve("property.resolutionWithList", dummy, faker);

        // then
        assertThat(actual, is("Yo!"));
        verify(dummy).hello();
    }

    @Test
    public void resolveKeyWithMultiplePropertiesShouldJoinResults() {
        // given
        final Superhero person = mock(Superhero.class);
        final DummyService dummy = mock(DummyService.class);
        doReturn(person).when(faker).superhero();

        doReturn("Yo Superman!").when(dummy).hello();
        doReturn("up up and away").when(person).descriptor();

        // when
        String actual = fakeValuesService.resolve("property.multipleResolution", dummy, faker);

        // then
        assertThat(actual, is("Yo Superman! up up and away"));

        verify(faker).superhero();
        verify(person).descriptor();
        verify(dummy).hello();
    }

    @Test
    public void testLocaleChain() {
        final List<Locale> chain = fakeValuesService.localeChain(Locale.SIMPLIFIED_CHINESE);

        assertThat(chain, contains(Locale.SIMPLIFIED_CHINESE, Locale.CHINESE, Locale.ENGLISH));
    }

    @Test
    public void testLocaleChainEnglish() {
        final List<Locale> chain = fakeValuesService.localeChain(Locale.ENGLISH);

        assertThat(chain, contains(Locale.ENGLISH));
    }

    @Test
    public void testLocaleChainLanguageOnly() {
        final List<Locale> chain = fakeValuesService.localeChain(Locale.CHINESE);

        assertThat(chain, contains(Locale.CHINESE, Locale.ENGLISH));
    }

    @Test
    public void expressionWithInvalidFakerObject() {
        expressionShouldFailWith("#{ObjectNotOnFaker.methodName}",
                "Unable to resolve #{ObjectNotOnFaker.methodName} directive.");
    }

    @Test
    public void expressionWithValidFakerObjectButInvalidMethod() {
        expressionShouldFailWith("#{Name.nonExistentMethod}",
                "Unable to resolve #{Name.nonExistentMethod} directive.");
    }

    /**
     * Two things are important here:
     * 1) the message in the exception should be USEFUL
     * 2) a {@link RuntimeException} should be thrown.
     *
     * if the message changes, it's ok to update the test provided
     * the two conditions above are still true.
     */
    @Test
    public void expressionWithValidFakerObjectValidMethodInvalidArgs() {
        expressionShouldFailWith("#{Number.number_between 'x','y'}",
                "Unable to resolve #{Number.number_between 'x','y'} directive.");
    }

    @Test
    public void futureDateExpression() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy" );

        Date now = new Date();
        Date nowPlus10Days = new Date( now.getTime() + MILLIS_IN_A_DAY * 10 );

        Date date = dateFormat.parse( fakeValuesService.expression( "#{date.future '10','TimeUnit.DAYS'}", faker ));

        assertThat( date.getTime(), greaterThan( now.getTime() ));
        assertThat( date.getTime(), lessThan( nowPlus10Days.getTime() ));
    }

    @Test
    public void pastDateExpression() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy" );

        Date now = new Date();
        Date nowMinus5Hours = new Date( now.getTime() - MILLIS_IN_AN_HOUR * 5 );

        Date date = dateFormat.parse( fakeValuesService.expression( "#{date.past '5','TimeUnit.HOURS'}", faker ));

        assertThat( date.getTime(), greaterThan( nowMinus5Hours.getTime() ));
        assertThat( date.getTime(), lessThan( now.getTime() ));
    }

    /**
     * Two things are important here:
     * 1) the message in the exception should be USEFUL
     * 2) a {@link RuntimeException} should be thrown.
     *
     * if the message changes, it's ok to update the test provided
     * the two conditions above are still true.
     */
    @Test
    public void expressionCompletelyUnresolvable() {
        expressionShouldFailWith("#{x}", "Unable to resolve #{x} directive.");
    }

    private void expressionShouldFailWith(String expression, String errorMessage) {
        try {
            fakeValuesService.expression(expression, faker);
            fail("Should have failed with RuntimeException and message of " + errorMessage);
        } catch (RuntimeException re) {
            assertThat(re.getMessage(), is(errorMessage));
        }
    }
    @Test
    public void resolveUsingTheSameKeyTwice() {
        // #{hello} -> DummyService.hello

        // given
        final DummyService dummy = mock(DummyService.class);
        when(dummy.hello()).thenReturn("1").thenReturn("2");

        // when
        final String actual = fakeValuesService.resolve("property.sameResolution", dummy, faker);

        // then
        assertThat(actual, is("1 2"));
        verifyZeroInteractions(faker);
    }

    public static class DummyService {
        public String firstName() {
            return "John";
        }

        public String lastName() {
            return "Smith";
        }

        public String hello() {
            return "Hello";
        }
    }
}
