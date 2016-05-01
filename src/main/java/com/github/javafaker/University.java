package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class University {

    private final Resolver resolver;
    private final FakeValuesServiceInterface fakeValueService;

    public University(Resolver resolver, FakeValuesServiceInterface fakeValueService) {
        this.resolver = resolver;
        this.fakeValueService = fakeValueService;
    }

    public String name() {
        return fakeValueService.resolve("university.name", this, resolver);
    }

    public String prefix() {
        return fakeValueService.fetchString("university.prefix");
    }

    public String suffix() {
        return fakeValueService.fetchString("university.suffix");
    }
}
