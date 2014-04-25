package com.github.javafaker.service;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

public class FakeValuesServiceTest {

    @Mock
    private RandomService randomService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(randomService.nextInt(anyInt())).thenReturn(0);
    }

    @Test
    public void localeShouldLoadWhenItExists() {
        new FakeValuesService(new Locale("test"), randomService);
    }

    @Test(expected = LocaleDoesNotExistException.class)
    public void localeShouldThrowException() {
        new FakeValuesService(new Locale("Does not exist"), randomService);
    }

    @Test
    public void fetchStringShouldReturnValue() {
        final FakeValuesService fakeValueService = new FakeValuesService(new Locale("test"), randomService);
        assertThat(fakeValueService.fetchString("property.dummy"), is("x"));
    }
}
