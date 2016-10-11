package com.github.javafaker.service;

import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class FakeValuesServiceTest {

    @Mock
    private RandomService randomService;

    @Mock
    private Faker faker;

    private FakeValuesService fakeValuesService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        // always return the first element
        when(randomService.nextInt(anyInt())).thenReturn(0);
        fakeValuesService = new FakeValuesService(new Locale("test"), randomService);
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
    public void resolveKeyToPropertyWithAPropertyWithoutAnObject() {
        // #{hello} -> DummyService.hello

        // given
        final Faker faker = mock(Faker.class);
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
        final Faker faker = mock(Faker.class);
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
        final Faker faker = mock(Faker.class);
        final DummyService dummy = mock(DummyService.class);
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
        final Faker faker = mock(Faker.class);
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

    private static class AnotherDummyService {
        public String firstName() {
            return "John";
        }
    }
}
