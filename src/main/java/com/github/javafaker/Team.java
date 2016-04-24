package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Team {

    private final Resolver resolver;
    private final FakeValuesServiceInterface fakeValuesService;

    public Team(Resolver resolver, FakeValuesServiceInterface fakeValuesService) {
        this.resolver = resolver;
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        return fakeValuesService.resolve("team.name", this, resolver);
    }

    public String creature() {
        return fakeValuesService.fetchString("team.creature");
    }

    public String state() {
        return fakeValuesService.fetchString("address.state");
    }
}
