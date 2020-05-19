package com.github.javafaker;

/**
 * Use to generate information of a same country.
 * @author 韩霄
 * @date 2020/5/18
 */
public class Country {
    private final Faker faker;
    private final String flagUrl;
    private final String[] countryTotal;

    protected Country(Faker faker) {
        this.faker = faker;
        this.flagUrl = "http://flags.fmcdn.net/data/flags/w580/";
        this.countryTotal = getCountryTotal().split("/");
    }

    /**
     * Randomly fetch a total country information which seperated by "/"
     * For example, ramdomly fetch "au/aus/Australia/Canberra/Australian Dollar/AUD"
     * from country.yml
     * @return      a String, 5 "/"s and 6 information about a same country.
     */
    private String getCountryTotal(){
        return faker.fakeValuesService().resolve("country.total", this, faker);
    }

    /**
     * generate a URL of the graph of the country's flag.
     * @return      a URL
     */
    public String flag() {
        return flagUrl + countryTotal[0] + ".png";
    }

    /**
     * Generates a two-digit abbreviation of a country name
     * @return      a String of 2-bits.
     */
    public String countryCode2() {
        return countryTotal[0];
    }

    /**
     * Generates a 3-digit abbreviation of a country name
     * @return      a String of 3-bits.
     */
    public String countryCode3() {
        return countryTotal[1];
    }

    /**
     * Generates a total country name
     * @return      a String of name.
     */
    public String name() {
        return countryTotal[2];
    }

    /**
     * Generates the name of capital of the country we just generated.
     * @return      a String of capital name.
     */
    public String capital() {
        return countryTotal[3];
    }

    /**
     * Generates the currency of the country we just generated.
     * @return      a String currency name.
     */
    public String currency() {
        return countryTotal[4];
    }

    /**
     * Generates the currency abbreviation of the country we just generated.
     * @return      a String currency abbreviation.
     */
    public String currencyCode() {
        return countryTotal[5];
    }






}
