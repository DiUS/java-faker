package com.github.javafaker;

public class Stock {

	private final Faker faker;

	protected Stock(Faker faker) {
		this.faker = faker;
	}

	public String nsdqSymbol() {
		return faker.fakeValuesService().resolve("stock.symbol_nsdq", this, faker);
	}
	
	public String nyseSymbol() {
		return faker.fakeValuesService().resolve("stock.symbol_nyse", this, faker);
	}
	
}
