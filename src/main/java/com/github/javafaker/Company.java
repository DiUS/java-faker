package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

public class Company {
    private final FakeValuesService fakeValuesService;

    public Company(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        return fakeValuesService.fetchString("company.name");
    }
}
