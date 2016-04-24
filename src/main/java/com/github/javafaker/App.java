package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class App {

    private final Resolver resolver;
    private final FakeValuesServiceInterface fakeValuesService;

    public App(Resolver resolver, FakeValuesServiceInterface fakeValuesService) {
        this.resolver = resolver;
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        return fakeValuesService.fetchString("app.name");
    }

    public String version() {
        return fakeValuesService.numerify(fakeValuesService.fetchString("app.version"));
    }

    public String author() {
        return fakeValuesService.resolve("app.name", this, resolver);
    }
}
