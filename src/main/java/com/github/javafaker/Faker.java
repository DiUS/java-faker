package com.github.javafaker;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

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
        this.address = new Address(name, fakeValuesService);
        this.business = new Business(fakeValuesService);
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

    // name

    public Name name() {
        return name;
    }

    public String firstName() {
        return name.firstName();
    }

    public String lastName() {
        return name.lastName();
    }

    public String prefix() {
        return name.prefix();
    }

    public String suffix() {
        return name.suffix();
    }

    // internet

    public Internet internet() {
        return internet;
    }

    public String emailAddress() {
        return internet.emailAddress();
    }

    // phone number

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    // lorem

    public Lorem lorem() {
        return lorem;
    }

    public List<String> words(int num) {
        return lorem.words(num);
    }

    public List<String> words() {
        return lorem.words();
    }

    public String sentence(int wordCount) {
        return lorem.sentence(wordCount);
    }

    public String sentence() {
        return lorem.sentence();
    }

    public List<String> sentences(int sentenceCount) {
        return lorem.sentences(sentenceCount);
    }

    public String paragraph(int sentenceCount) {
        return lorem.paragraph(sentenceCount);
    }

    public String paragraph() {
        return lorem.paragraph();
    }

    public List<String> paragraphs(int paragraphCount) {
        return lorem.paragraphs(paragraphCount);
    }

    // address

    public Address address() {
        return address;
    }

    public String streetName() {
        return address.streetName();
    }

    public String streetAddressNumber() {
        return address.streetAddressNumber();
    }

    public String streetAddress(boolean includeSecondary) {
        return address.streetAddress(includeSecondary);
    }

    public String secondaryAddress() {
        return address.secondaryAddress();
    }

    public String zipCode() {
        return address.zipCode();
    }

    public String streetSuffix() {
        return address.streetSuffix();
    }

    public String citySuffix() {
        return address.citySuffix();
    }

    public String cityPrefix() {
        return address.cityPrefix();
    }

    public String stateAbbr() {
        return address.stateAbbr();
    }

    public String country() {
        return address.country();
    }

    public Business business() {
        return business;
    }
}
