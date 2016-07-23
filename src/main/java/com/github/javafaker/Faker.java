package com.github.javafaker;

import com.github.javafaker.service.DefaultingFakeValuesService;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.reflect.MethodUtils;

import java.lang.reflect.Proxy;
import java.util.Locale;
import java.util.Random;

/**
 * Provides utility methods for generating fake strings, such as names, phone
 * numbers, addresses. generate random strings with given patterns
 *
 * @author ren
 */
public class Faker implements Resolver {
    private final RandomService randomService;
    private final FakeValuesService fakeValuesService;
    private final App app;
    private final Lorem lorem;
    private final Name name;
    private final Number number;
    private final Internet internet;
    private final PhoneNumber phoneNumber;
    private final Address address;
    private final Business business;
    private final Book book;
    private final ChuckNorris chuckNorris;
    private final Color color;
    private final Commerce commerce;
    private final Company company;
    private final Crypto crypto;
    private final IdNumber idNumber;
    private final Hacker hacker;
    private final Options options;
    private final Code code;
    private final Finance finance;
    private final DateAndTime dateAndTime;
    private final Educator educator;
    private final Shakespeare shakespeare;
    private final Superhero superhero;
    private final Bool bool;
    private final Team team;
    private final Beer beer;
    private final University university;
    private final Cat cat;

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
        FakeValuesService defaultEnglishFakeValuesService = new FakeValuesService(Locale.ENGLISH, randomService);
        FakeValuesServiceInterface proxiedFakeValueService = createProxiedFakeValuesService(fakeValuesService,
                defaultEnglishFakeValuesService);

        this.app = new App(this, proxiedFakeValueService);
        this.lorem = new Lorem(proxiedFakeValueService, randomService);
        this.name = new Name(this, proxiedFakeValueService);
        this.number = new Number(randomService);
        this.internet = new Internet(name, lorem, proxiedFakeValueService, randomService);
        this.phoneNumber = new PhoneNumber(proxiedFakeValueService);
        this.address = new Address(this, name, proxiedFakeValueService, randomService);
        this.book = new Book(this, proxiedFakeValueService);
        this.business = new Business(proxiedFakeValueService);
        this.chuckNorris = new ChuckNorris(proxiedFakeValueService);
        this.color = new Color(proxiedFakeValueService);
        this.idNumber = new IdNumber(this, proxiedFakeValueService);
        this.hacker = new Hacker(proxiedFakeValueService);
        this.company = new Company(this, proxiedFakeValueService, randomService);
        this.crypto = new Crypto(lorem);
        this.commerce = new Commerce(proxiedFakeValueService, randomService);
        this.options = new Options(randomService);
        this.code = new Code(randomService);
        this.finance = new Finance(proxiedFakeValueService, randomService);
        this.dateAndTime = new DateAndTime(randomService);
        this.educator = new Educator(proxiedFakeValueService);
        this.shakespeare = new Shakespeare(randomService);
        this.superhero = new Superhero(this, proxiedFakeValueService);
        this.team = new Team(this, proxiedFakeValueService);
        this.bool = new Bool(randomService);
        this.beer = new Beer(proxiedFakeValueService);
        this.university = new University(this, proxiedFakeValueService);
        this.cat = new Cat(proxiedFakeValueService);
    }

    private static FakeValuesServiceInterface createProxiedFakeValuesService(FakeValuesServiceInterface fakeValuesServiceInterface,
                                                                                      FakeValuesServiceInterface defaultFakeValuesServiceInterface) {
        return (FakeValuesServiceInterface) Proxy.newProxyInstance(Faker.class.getClassLoader(),
                new Class[]{FakeValuesServiceInterface.class},
                new DefaultingFakeValuesService(fakeValuesServiceInterface,
                                                defaultFakeValuesServiceInterface));
    }

    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     * <p>
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
     * <p>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @return
     */
    public String letterify(String letterString) {
        return fakeValuesService.letterify(letterString);
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @param isUpper
     * @return
     */
    public String letterify(String letterString, boolean isUpper) {
        return fakeValuesService.letterify(letterString, isUpper);
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

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String)}
     * over the incoming string.
     *
     * @param string
     * @param isUpper
     * @return
     */
    public String bothify(String string, boolean isUpper) {
        return fakeValuesService.bothify(string, isUpper);
    }

    /**
     * Generates a String that matches the given regular expression.
     */
    public String regexify(String regex) {
        return fakeValuesService.regexify(regex);
    }

    public App app() {
        return app;
    }

    public Name name() {
        return name;
    }

    public Number number() { return number; }

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

    public Book book() {
        return book;
    }

    public Business business() {
        return business;
    }

    public ChuckNorris chuckNorris() {
        return chuckNorris;
    }

    public Color color() {
        return color;
    }

    public Commerce commerce() {
        return commerce;
    }

    public Company company() {
        return company;
    }

    public Crypto crypto() {
        return crypto;
    }

    public Hacker hacker() {
        return hacker;
    }
 
    public IdNumber idNumber() {
        return idNumber;
    }

    public Options options() {
        return options;
    }

    public Code code() {
        return code;
    }

    public Finance finance() {
        return finance;
    }

    public DateAndTime date() {
        return dateAndTime;
    }

    public Educator educator() {
        return educator;
    }
    
    public Shakespeare shakespeare() {
        return shakespeare;
    }

    public Superhero superhero() {
        return superhero;
    }
    
    public Bool bool() {
        return bool;
    }

    public Team team() {
        return team;
    }

    public Beer beer() {
        return beer;
    }

    public University university() {
        return university;
    }

    public Cat cat() {
        return cat;
    }

    /**
     * Resolves a key in the format of class.method_name
     *
     * @param key
     * @return
     */
    public String resolve(String key) {
        String[] keySplit = key.split("\\.", 2);
        String object = WordUtils.uncapitalize(keySplit[0]);
        String methodName = keySplit[1];

        char[] METHOD_NAME_REPLACEMENT = {'_'};
        methodName = WordUtils.capitalizeFully(methodName, METHOD_NAME_REPLACEMENT).replaceAll("_", "");
        methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
        try {
            Object objectWithMethodToInvoke = MethodUtils.invokeMethod(this, object, null);
            return (String) MethodUtils.invokeMethod(objectWithMethodToInvoke, methodName, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
