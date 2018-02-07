package com.github.javafaker;

/**
 * A generator for Weather data.
 */
public class Weather {

    private static final int DEFAULT_MIN_TEMP_C = -30;
    private static final int DEFAULT_MAX_TEMP_C = 38;
    private static final int DEFAULT_MIN_TEMP_F = -22;
    private static final int DEFAULT_MAX_TEMP_F = 100;

    private final Faker faker;

    protected Weather(Faker faker) {
        this.faker = faker;
    }

    /**
     * Generates a short weather description.
     */
    public String description() {
        return faker.resolve("weather.description");
    }

    /**
     * Generates a random temperature celsius between -30 and 38 degrees.
     * @return String that represents temperature in format 5°C
     */
    public String temperatureCelsius() {
        return temperature(DEFAULT_MIN_TEMP_C, DEFAULT_MAX_TEMP_C, "weather.temperature.celsius");
    }

    /**
     * Generates a random temperature fahrenheit between -22 and 100 degrees.
     * @return String that represents temperature in format 5°F
     */
    public String temperatureFahrenheit() {
        return temperature(DEFAULT_MIN_TEMP_F, DEFAULT_MAX_TEMP_F, "weather.temperature.fahrenheit");
    }

    /**
     * Generates a random temperature celsius between two temperatures.
     * @param minTemperature the minimal temperature
     * @param maxTemperature the maximal temperature
     * @return String that represents temperature in format 5°C
     */
    public String temperatureCelsius(int minTemperature, int maxTemperature) {
        return temperature(minTemperature, maxTemperature, "weather.temperature.celsius");
    }

    /**
     * Generates a random temperature fahrenheit between two temperatures.
     * @param minTemperature the minimal temperature
     * @param maxTemperature the maximal temperature
     * @return String that represents temperature in format 5°F
     */
    public String temperatureFahrenheit(int minTemperature, int maxTemperature) {
        return temperature(minTemperature, maxTemperature, "weather.temperature.fahrenheit");
    }

    private String temperature(int minTemperature, int maxTemperature, String degreeKey) {
        return faker.random().nextInt(minTemperature, maxTemperature) + faker.resolve(degreeKey);
    }
}
