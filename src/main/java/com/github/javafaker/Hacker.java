package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Hacker {

    private final FakeValuesServiceInterface fakeValuesService;

    public Hacker(FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String abbreviation() {
        return fakeValuesService.fetchString("hacker.abbreviation");
    }

    public String adjective() {
        return fakeValuesService.fetchString("hacker.adjective");
    }

    public String noun() {
        return fakeValuesService.fetchString("hacker.noun");
    }

    public String verb() {
        return fakeValuesService.fetchString("hacker.verb");
    }

    public String ingverb() {
        return fakeValuesService.fetchString("hacker.ingverb");
    }
}
