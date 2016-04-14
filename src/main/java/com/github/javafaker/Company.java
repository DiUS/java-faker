package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Company {
    private final FakeValuesServiceInterface fakeValuesService;
    private final Resolver resolver;

    public Company(Resolver resolver, FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
        this.resolver = resolver;
    }

    public String name() {
        return fakeValuesService.resolve("company.name", this, resolver);
    }

    public String suffix() {
        return fakeValuesService.safeFetch("company.suffix");
    }

    public String industry() {
        return fakeValuesService.safeFetch("company.industry");
    }

    public String profession() {
        return fakeValuesService.safeFetch("company.profession");
    }
}
