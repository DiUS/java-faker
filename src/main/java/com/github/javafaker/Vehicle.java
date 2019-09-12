package com.github.javafaker;

import java.util.ArrayList;
import java.util.Calendar;

public class Vehicle {
    private final Faker faker;
    static final String VIN_REGEX = "[A-Z0-9]{3}[A-Z0-9]{5}[A-Z0-9]{1}[A-Z0-9]{1}[A-Z0-0]{1}[A-Z0-9]{1}\\d{5}";

    protected Vehicle(Faker faker) {
        this.faker = faker;
    }

    public String vin () {
        return faker.fakeValuesService().regexify(VIN_REGEX);
    }

    public String manufacture () {
        return faker.fakeValuesService().fetchString("vehicle.manufacture");
    }

    public String make () {
        return faker.fakeValuesService().fetchString("vehicle.makes");
    }

    public String model(String make) {
        ArrayList<String> arr = (ArrayList<String>) faker.fakeValuesService().fetchObject("vehicle.makes");
        if (arr.contains(make))
            return faker.fakeValuesService().fetchString(String.format("vehicle.models_by_make.%s", make));
        else
            return "Only Supports models of the following make: " + arr;
    }

    public String makeAndModel() {
        String make = make();
        return make + " " + model(make);
    }

    public String style () {
        return faker.fakeValuesService().fetchString("vehicle.styles");
    }

    public String color () {
        return faker.fakeValuesService().fetchString("vehicle.colors");
    }

    public String transmission () {
        return faker.fakeValuesService().fetchString("vehicle.transmissions");
    }

    public String driveType() {
        return faker.fakeValuesService().fetchString("vehicle.drive_types");
    }

    public String fuelType() {
        return faker.fakeValuesService().fetchString("vehicle.fuel_types");
    }

    public String carType() {
        return faker.fakeValuesService().fetchString("vehicle.car_types");
    }

    public String engine() {
        return faker.fakeValuesService().fetch("vehicle.engine_sizes") + " " + faker.fakeValuesService().fetchObject("vehicle.cylinder_engine");
    }

    public ArrayList carOption() {
        int optionSize =faker.number().numberBetween(5, 10);
        ArrayList<String> arr = new ArrayList<String>(optionSize);
        while (optionSize > 0) {
            arr.add(faker.fakeValuesService().fetchString("vehicle.car_options"));
            optionSize -= 1;
        }
        return arr;
    }

    public ArrayList standardSpecs() {
        int standardSpecsSize =faker.number().numberBetween(5, 10);
        ArrayList<String> arr = new ArrayList<String>(standardSpecsSize);
        while (standardSpecsSize > 0) {
            arr.add(faker.fakeValuesService().fetchString("vehicle.standard_specs"));
            standardSpecsSize -= 1;
        }
        return arr;
    }

    public Object door () {
        return faker.fakeValuesService().fetch("vehicle.doors");
    }

    public int year() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return faker.number().numberBetween(currentYear - 10, currentYear);
    }

    public int mileage(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public String licensePlate(String state_abbreviation) {
        String key;
        if (state_abbreviation != null && !state_abbreviation.isEmpty()) {
            key = "vehicle.license_plate_by_state." + state_abbreviation;
            return faker.regexify(faker.bothify(faker.fakeValuesService().fetchObject(key).toString()));
        } else
            key = "vehicle.license_plate";
            return faker.regexify(faker.bothify(faker.fakeValuesService().fetchObject(key).toString()));
    }
}
