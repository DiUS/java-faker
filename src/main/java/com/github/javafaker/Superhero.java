package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Superhero {
    private final Resolver resolver;
    private final FakeValuesServiceInterface fakeValueService;

    public Superhero(Resolver resolver,
                     FakeValuesServiceInterface fakeValueService) {
        this.resolver = resolver;
        this.fakeValueService = fakeValueService;
    }

    public String name() {
        return fakeValueService.resolve("superhero.name", this, resolver);
    }

    public String prefix() {
        return fakeValueService.fetchString("superhero.prefix");
    }

    public String suffix() {
        return fakeValueService.fetchString("superhero.suffix");
    }

    public String power() {
        return fakeValueService.fetchString("superhero.power");
    }

    public String descriptor() {
        return fakeValueService.fetchString("superhero.descriptor");
    }
}
