package com.github.javafaker.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;

public class FakeValuesGroupingTest {

    private FakeValuesGrouping fakeValuesGrouping;
    private FakeValues addressValues;

    @Before
    public void before() {
        fakeValuesGrouping = new FakeValuesGrouping();
        addressValues = new FakeValues(Locale.ENGLISH, "address.yml", "address");
        fakeValuesGrouping.add(addressValues);
    }

    @Test
    public void handlesOneFakeValue() {
        assertThat(Maps.difference(fakeValuesGrouping.get("address"), addressValues.get("address")).areEqual(), equalTo(true));
        assertThat(fakeValuesGrouping.get("address"), is(notNullValue()));
    }

    @Test
    public void handlesMultipleFakeValues() {
        FakeValues catValues = new FakeValues(Locale.ENGLISH, "cat.yml", "creature");
        fakeValuesGrouping.add(catValues);

        assertThat(Maps.difference(fakeValuesGrouping.get("address"), addressValues.get("address")).areEqual(), equalTo(true));
        assertThat(fakeValuesGrouping.get("address"), is(notNullValue()));

        assertThat(Maps.difference(fakeValuesGrouping.get("creature"), catValues.get("creature")).areEqual(), equalTo(true));
        assertThat(fakeValuesGrouping.get("creature"), is(notNullValue()));
    }
    
}