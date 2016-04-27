package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class ChuckNorris {

    private final FakeValuesServiceInterface fakeValuesService;

    public ChuckNorris(FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String fact() {
        return fakeValuesService.fetchString("chuck_norris.fact");
    }
}
