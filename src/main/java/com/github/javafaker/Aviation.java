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

    /**
     * Return a flight number (IATA or ICAO format)
     */
    public String flight(String type) {
        String airline;
        if (type.equals("ICAO")) {
            airline = faker.fakeValuesService().fetchString("aviation.ICAO_airline");
        }
        else {
            airline = faker.fakeValuesService().fetchString("aviation.IATA_airline");
        }
        int number = faker.number().numberBetween(0,9999);
        return airline + number;
    }

    /**
     * Return a flight number without specifying flight number type
     */
    public String flight() {
        return flight("IATA");
    }

    /**
     * Return an airline name
     */
    public String airline() { return faker.fakeValuesService().fetchString("aviation.airline");}
}

