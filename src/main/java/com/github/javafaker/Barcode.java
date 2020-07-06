package com.github.javafaker;

public class Barcode {

    private Faker faker;

    public Barcode(Faker faker) {
        this.faker = faker;
    }

    public String type() {
        return faker.resolve("barcode.types");
    }

    public String data(){
        return faker.resolve("barcode.datas");
    }

    public String typeAndData(){
        return faker.resolve("barcode.typeAndData");
    }
}
