package com.github.javafaker.service;

public class LocaleDoesNotExistException extends RuntimeException {
    public LocaleDoesNotExistException(String message) {
        super(message);
    }
}
