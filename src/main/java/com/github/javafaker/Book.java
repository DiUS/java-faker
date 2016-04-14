package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;

public class Book {
    private final FakeValuesServiceInterface fakeValuesService;
    private final Resolver resolver;

    public Book(Resolver resolver, FakeValuesServiceInterface fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
        this.resolver = resolver;
    }

    public String author() {
        return fakeValuesService.resolve("book.author", this, resolver);
    }

    public String title() {
        return fakeValuesService.fetchString("book.title");
    }

    public String publisher() {
        return fakeValuesService.fetchString("book.publisher");
    }

    public String genre() {
        return fakeValuesService.fetchString("book.genre");
    }
}
