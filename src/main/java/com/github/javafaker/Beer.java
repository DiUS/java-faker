package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Beer {

    private final FakeValuesServiceInterface fakeValuesService;

    public Beer(FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        return fakeValuesService.fetchString("beer.name");
    }

    public String style() {
        return fakeValuesService.fetchString("beer.style");
    }

    public String hop() {
        return fakeValuesService.fetchString("beer.hop");
    }

    public String yeast() {
        return fakeValuesService.fetchString("beer.yeast");
    }

    public String malt() {
        return fakeValuesService.fetchString("beer.malt");
    }
}
