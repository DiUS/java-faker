package com.github.javafaker;

/**
 * Generates aviation related strings.
 */
public class Aviation {

    private final Faker faker;

    protected Aviation(Faker faker) {
        this.faker = faker;
    }

    /**
     * @return some aircraft name/model/make e.g. "An-2".
     */
    public String aircraft() {
        return faker.fakeValuesService().fetchString("aviation.aircraft");
    }

    /**
     * Returns an airport ICAO code.
     * See also: https://en.wikipedia.org/wiki/List_of_airports_by_ICAO_code:_A
     */
    public String airport() {
        return faker.fakeValuesService().fetchString("aviation.airport");
    }

    /**
     * Provides a METAR weather report.
     * Have a look at https://en.wikipedia.org/wiki/METAR
     */
    public String METAR() {
        return faker.fakeValuesService().fetchString("aviation.metar");
    }
}