package com.github.javafaker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeValuesGrouping implements FakeValuesInterface {

    private List<FakeValues> fakeValuesList = new ArrayList<FakeValues>();

    public void add(FakeValues fakeValues) {
        fakeValuesList.add(fakeValues);
    }

    @Override
    public Object get(String key) {
        Object result = null;
        for (FakeValues fakeValues : fakeValuesList) {
            if (fakeValues.supportsPath(key)) {
                if (result != null) {
                    final Object newResult = fakeValues.get(key);
                    if (isMergeable(newResult) && isMergeable(result)) {
                        result = merge(newResult, result);
                    }
                } else {
                    result = fakeValues.get(key);
                }
            }

            // if we get a result that's not mergable, return
            if (result != null && !isMergeable(result)) {
                return result;
            }

        }
        return result;
    }

    private static Map merge(Object result1, Object result2) {
        Map map = new HashMap((Map) result1);
        map.putAll((Map) result2);
        return map;
    }

    private static boolean isMergeable(Object result) {
        return result instanceof Map;
    }
}
