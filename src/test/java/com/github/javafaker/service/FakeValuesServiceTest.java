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

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class FakeValuesServiceTest extends AbstractFakerTest {

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

    @Test(expected = LocaleDoesNotExistException.class)
    public void localeShouldThrowException() {
        new FakeValuesService(new Locale("Does not exist"), randomService);
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
        assertThat(value, either(is("55")).or(is("44")).or(is("45")).or(is("54")));
        verify(faker).regexify("[45]{2}");
    }

    @Test
    public void regexifyDirective2() {
        final DummyService dummy = mock(DummyService.class);

        String value = fakeValuesService.resolve("property.regexify_cell", dummy, faker);
        assertThat(value, either(is("479")).or(is("459")));
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
        
        assertThat(chain, hasSize(3));
        assertThat(chain.get(0), is(Locale.SIMPLIFIED_CHINESE));
        assertThat(chain.get(1), is(Locale.CHINESE));
        assertThat(chain.get(2), is(Locale.ENGLISH));
        
    }
    
    @Test
    public void testLocaleChainEnglish() {
        final List<Locale> chain = fakeValuesService.localeChain(Locale.ENGLISH);

        assertThat(chain, hasSize(1));
        assertThat(chain.get(0), is(Locale.ENGLISH));
    }
    
    @Test
    public void testLocaleChainLanguageOnly() {
        final List<Locale> chain = fakeValuesService.localeChain(Locale.CHINESE);

        assertThat(chain, hasSize(2));
        assertThat(chain.get(0), is(Locale.CHINESE));
        assertThat(chain.get(1), is(Locale.ENGLISH));
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
