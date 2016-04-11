package com.github.javafaker.service;

import com.github.javafaker.Resolver;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Locale;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FakeValuesServiceTest {

    @Mock
    private RandomService randomService;

    @Mock
    private Resolver resolver;

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
        assertThat(fakeValuesService.safeFetch("property.dummy"), is("x"));
    }

    @Test
    public void safeFetchShouldReturnSimpleList() {
        assertThat(fakeValuesService.safeFetch("property.simple"), is("hello"));
    }

    @Test
    public void safeFetchShouldReturnEmptyStringWhenPropertyDoesntExist() {
        assertThat(fakeValuesService.safeFetch("property.dummy2"), isEmptyString());
    }

    @Test
    public void resolveKeyToPropertyWithAPropertyWithoutAnObject() {
        // #{hello} -> DummyService.hello
        when(resolver.resolve(anyString())).thenReturn("");

        fakeValuesService.resolve("property.simpleResolution", new DummyService(), resolver);

        verify(resolver).resolve(eq("DummyService.hello"));
    }

    @Test
    public void resolveKeyToPropertyWithAPropertyWithAnObject() {
        // #{person.hello} -> Person.hello
        when(resolver.resolve(anyString())).thenReturn("");

        fakeValuesService.resolve("property.advancedResolution", new DummyService(), resolver);

        verify(resolver).resolve(eq("Person.hello"));
    }

    @Test
    public void resolveKeyToPropertyWithAList() {
        // #{hello} -> DummyService.hello
        when(resolver.resolve(anyString())).thenReturn("");

        fakeValuesService.resolve("property.resolutionWithList", new DummyService(), resolver);

        verify(resolver).resolve(eq("DummyService.hello"));
    }

    @Test
    public void resolveKeyWithMultiplePropertiesShouldJoinResults() {
        when(resolver.resolve("DummyService.hello")).thenReturn("1");
        when(resolver.resolve("Person.hello")).thenReturn("2");

        String resolved = fakeValuesService.resolve("property.multipleResolution", new DummyService(), resolver);
        assertThat(resolved, is("1 2"));
    }

    private static class DummyService {
        public String firstName() {
            return "John";
        }

        public String lastName() {
            return "Smith";
        }
    }

    private static class AnotherDummyService {
        public String firstName() {
            return "John";
        }
    }
}
