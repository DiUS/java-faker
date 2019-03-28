package com.github.javafaker.service;

import java.util.ArrayList;
import java.util.List;

public class FakeValuesGrouping implements FakeValuesInterface {

    private List<FakeValues> fakeValuesList = new ArrayList<FakeValues>();

    public void add(FakeValues fakeValues) {
        fakeValuesList.add(fakeValues);
    }

    @Override
    public Object get(String key) {
        for (FakeValues fakeValues : fakeValuesList) {
            if (fakeValues.supportsPath(key)) {
                return fakeValues.get(key);
            }
        }
        return null;
    }
}
