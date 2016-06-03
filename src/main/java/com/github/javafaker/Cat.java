package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Cat {
    private final FakeValuesServiceInterface fakeValuesService;

    public Cat(FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        return fakeValuesService.fetchString("cat.name");
    }

    public String breed() {
        return fakeValuesService.fetchString("cat.breed");
    }

    public String registry() {
        return fakeValuesService.fetchString("cat.registry");
    }
}
