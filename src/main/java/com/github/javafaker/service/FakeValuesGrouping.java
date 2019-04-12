package com.github.javafaker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FakeValuesGrouping implements FakeValuesInterface {

    private List<FakeValues> fakeValuesList = new ArrayList<FakeValues>();

    public void add(FakeValues fakeValues) {
        fakeValuesList.add(fakeValues);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map get(String key) {
        Map result = null;
        for (FakeValues fakeValues : fakeValuesList) {
            if (fakeValues.supportsPath(key)) {
                if (result != null) {
                    final Map newResult = fakeValues.get(key);
                    result.putAll(newResult);
                } else {
                    result = fakeValues.get(key);
                }
            }
        }
        return result;
    }
}
