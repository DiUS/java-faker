package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;
import java.util.Random;

/**
 * Provides utility methods for generating fake strings, such as names, phone
 * numbers, addresses. generate random strings with given patterns
 *
 * @author ren
 */
public class Faker {
    private final RandomService randomService;
    private final FakeValuesService fakeValuesService;
    private final Lorem lorem;
    private final Name name;
    private final Internet internet;
    private final PhoneNumber phoneNumber;
    private final Address address;
    private final Business business;
    private final Options options;
    private final Code code;
    private final Finance finance;
    private final DateAndTime dateAndTime;

    public Faker() {
        this(Locale.ENGLISH);
    }

    public Faker(Locale locale) {
        this(locale, null);
    }

    public Faker(Random random) {
        this(Locale.ENGLISH, random);
    }

    public Faker(Locale locale, Random random) {
        this.randomService = new RandomService(random);
        this.fakeValuesService = new FakeValuesService(locale, randomService);
        this.lorem = new Lorem(fakeValuesService, randomService);
        this.name = new Name(fakeValuesService);
        this.internet = new Internet(name, fakeValuesService);
        this.phoneNumber = new PhoneNumber(fakeValuesService);
        this.address = new Address(name, fakeValuesService, randomService);
        this.business = new Business(fakeValuesService);
        this.options = new Options(randomService);
        this.code = new Code(randomService);
        this.finance = new Finance(fakeValuesService, randomService);
        this.dateAndTime = new DateAndTime(randomService);
    }

    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     *
     * For example, the string "ABC##EFG" could be replaced with a string like "ABC99EFG".
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        return fakeValuesService.numerify(numberString);
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * 
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @return
     */
    public String letterify(String letterString) {
        return fakeValuesService.letterify(letterString);
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String)}
     * over the incoming string.
     *
     * @param string
     * @return
     */
    public String bothify(String string) {
        return fakeValuesService.bothify(string);
    }

    public Name name() {
        return name;
    }

    public Internet internet() {
        return internet;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Lorem lorem() {
        return lorem;
    }

    public Address address() {
        return address;
    }

    public Business business() {
        return business;
    }

    public Options options() {
        return options;
    }

    public Code code() {
        return code;
    }

    public Finance finance() { return finance; }

    public DateAndTime date() {
        return dateAndTime;
    }

}
