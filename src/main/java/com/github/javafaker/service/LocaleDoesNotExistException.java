package com.github.javafaker.service;

public class LocaleDoesNotExistException extends RuntimeException {
    private static final long serialVersionUID = 924974744056205070L;

    public LocaleDoesNotExistException(String message) {
        super(message);
    }
}
