package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

public class Name {
    private final FakeValuesService fakeValuesService;

    public Name(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }

    public String name() {
        return fakeValuesService.composite("name.formats", " ", this);
    }

    public String fullName() {
        return name();
    }

    public String firstName() {
        return fakeValuesService.fetchString("name.first_name");
    }

    public String lastName() {
        return fakeValuesService.fetchString("name.last_name");
    }

    public String prefix() {
        return fakeValuesService.fetchString("name.prefix");
    }

    public String suffix() {
        return fakeValuesService.fetchString("name.suffix");
    }
}
