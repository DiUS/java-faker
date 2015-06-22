package com.github.javafaker;

public class Country {
    
    private final String name;
    private final String iso;

    public Country(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }
    
    public String name() {
        return name;
    }
    
    public String iso() {
        return iso;
    }
}
