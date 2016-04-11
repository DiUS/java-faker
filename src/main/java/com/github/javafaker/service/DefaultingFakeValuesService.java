package com.github.javafaker.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DefaultingFakeValuesService implements InvocationHandler {

    private final FakeValuesServiceInterface fakeValuesServiceInterface;
    private final FakeValuesServiceInterface defaultFakeValuesService;

    public DefaultingFakeValuesService(FakeValuesServiceInterface fakeValuesServiceInterface,
                                       FakeValuesServiceInterface defaultFakeValuesService) {
        this.fakeValuesServiceInterface = fakeValuesServiceInterface;
        this.defaultFakeValuesService = defaultFakeValuesService;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object value = method.invoke(fakeValuesServiceInterface, args);
            if (value == null || isEmptyString(value)) {
                return defaultValue(method, args);
            } else {
                return value;
            }
        } catch (Exception e) {
            return defaultValue(method, args);
        }
    }

    private static boolean isEmptyString(Object value) {
        return (value instanceof String) && ((String) value).isEmpty();
    }

    private Object defaultValue(Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        return method.invoke(defaultFakeValuesService, args);
    }
}
