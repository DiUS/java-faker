package com.github.javafaker;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class Vehicle {
    private final Faker faker;

    static final String VIN_REGEX = "[A-Z0-9]{3}[A-Z0-9]{5}[A-Z0-9]{1}[A-Z0-9]{1}[A-Z0]{1}[A-Z0-9]{1}\\d{5}";

    public Vehicle(Faker faker) {
        this.faker = faker;
    }

    public String vin() { return faker.fakeValuesService().regexify(VIN_REGEX); }

    public String manufacturer() {  return faker.fakeValuesService().fetchString("vehicle.manufacture"); }

    public String make() { return faker.fakeValuesService().fetchString("vehicle.makes"); }

    @SuppressWarnings("unchecked")
    public String model(String make) { return faker.fakeValuesService().fetchString("vehicle.models_by_make." + make); }

    public String makeAndModel() {
        String make = make();
        return make + " " + model(make);
    }

    public String style() { return faker.fakeValuesService().fetchString("vehicle.styles"); }

    public String color() { return faker.fakeValuesService().fetchString("vehicle.colors"); }

    public String transmission() { return faker.fakeValuesService().fetchString("vehicle.transmissions"); }

    public String driveType() { return faker.fakeValuesService().fetchString("vehicle.drive_types"); }

    public String fuelType() { return faker.fakeValuesService().fetchString("vehicle.fuel_types"); }

    public String carType() { return faker.fakeValuesService().fetchString("vehicle.car_types"); }

    public String engine() {
        return faker.fakeValuesService().fetch("vehicle.engine_sizes")
                + " "
                + faker.fakeValuesService().fetchObject("vehicle.cylinder_engine");
    }

    public ArrayList carOptions() { return carOptions(5, 10); }

    public ArrayList carOptions(int min, int max) {
        int optionSize =faker.number().numberBetween(min, max);
        ArrayList<String> arr = new ArrayList<String>(optionSize);
        while (optionSize > 0) {
            arr.add(faker.fakeValuesService().fetchString("vehicle.car_options"));
            optionSize -= 1;
        }
        return arr;
    }

    public ArrayList standardSpecs() { return standardSpecs(5, 10); }

    public ArrayList standardSpecs(int min, int max) {
        int standardSpecsSize =faker.number().numberBetween(min, max);
        ArrayList<String> arr = new ArrayList<String>(standardSpecsSize);
        while (standardSpecsSize > 0) {
            arr.add(faker.fakeValuesService().fetchString("vehicle.standard_specs"));
            standardSpecsSize -= 1;
        }
        return arr;
    }

    public Object doors() { return faker.fakeValuesService().fetch("vehicle.doors"); }

    public String licensePlate() {
        return faker.regexify(faker.bothify(faker.fakeValuesService().fetchObject("vehicle.license_plate").toString()));
    }

    public String licensePlate(String stateAbbreviation) {

        if(StringUtils.isEmpty(stateAbbreviation)) {
            return null;
        }

        Object licensePlatesByState = faker.fakeValuesService().fetchObject("vehicle.license_plate_by_state." + stateAbbreviation);
        return licensePlatesByState == null ? null :  faker.regexify(faker.bothify(licensePlatesByState.toString()));
    }
}
