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

    private final Ancient ancient;
    private final App app;
    private final Artist artist;
    private final Avatar avatar;
    private final Aviation aviation;
    private final Lorem lorem;
    private final Music music;
    private final Name name;
    private final Number number;
    private final Internet internet;
    private final PhoneNumber phoneNumber;
    private final Pokemon pokemon;
    private final Address address;
    private final Business business;
    private final Book book;
    private final ChuckNorris chuckNorris;
    private final Color color;
    private final Commerce commerce;
    private final Country country;
    private final Currency currency;
    private final Company company;
    private final Crypto crypto;
    private final IdNumber idNumber;
    private final Hacker hacker;
    private final Options options;
    private final Code code;
    private final Finance finance;
    private final Food food;
    private final GameOfThrones gameOfThrones;
    private final DateAndTime dateAndTime;
    private final Demographic demographic;
    private final Dog dog;
    private final Educator educator;
    private final ElderScrolls elderScrolls;
    private final Shakespeare shakespeare;
    private final SlackEmoji slackEmoji;
    private final Space space;
    private final Superhero superhero;
    private final Bool bool;
    private final Team team;
    private final Beer beer;
    private final University university;
    private final Cat cat;
    private final File file;
    private final Stock stock;
    private final LordOfTheRings lordOfTheRings;
    private final Zelda zelda;
    private final HarryPotter harryPotter;
    private final RockBand rockBand;
    private final Esports esports;
    private final Friends friends;
    private final Hipster hipster;
    private final Job job;
    private final TwinPeaks twinPeaks;
    private final RickAndMorty rickAndMorty;
    private final Yoda yoda;
    private final Matz matz;
    private final Witcher witcher;
    private final DragonBall dragonBall;
    private final FunnyName funnyName;
    private final HitchhikersGuideToTheGalaxy hitchhikersGuideToTheGalaxy;
    private final Hobbit hobbit;
    private final HowIMetYourMother howIMetYourMother;
    private final LeagueOfLegends leagueOfLegends;
    private final Overwatch overwatch;
    private final Robin robin;
    private final StarTrek starTrek;
    private final Weather weather;
    private final Lebowski lebowski;
    private final Medical medical;
    private final Animal animal;
    private final BackToTheFuture backToTheFuture;
    private final PrincessBride princessBride;
    private final Buffy buffy;
    private final Relationships relationships;
    private final Nation nation;
    private final Dune dune;

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

        this.ancient = new Ancient(this);
        this.app = new App(this);
        this.artist = new Artist(this);
        this.avatar = new Avatar(this);
        this.aviation = new Aviation(this);
        this.lorem = new Lorem(this);
        this.music = new Music(this);
        this.name = new Name(this);
        this.number = new Number(this);
        this.internet = new Internet(this);
        this.phoneNumber = new PhoneNumber(this);
        this.pokemon = new Pokemon(this);
        this.address = new Address(this);
        this.book = new Book(this);
        this.business = new Business(this);
        this.chuckNorris = new ChuckNorris(this);
        this.color = new Color(this);
        this.idNumber = new IdNumber(this);
        this.hacker = new Hacker(this);
        this.company = new Company(this);
        this.crypto = new Crypto(this);
        this.elderScrolls = new ElderScrolls(this);
        this.commerce = new Commerce(this);
        this.currency = new Currency(this);
        this.options = new Options(this);
        this.code = new Code(this);
        this.file = new File(this);
        this.finance = new Finance(this);
        this.food = new Food(this);
        this.gameOfThrones = new GameOfThrones(this);
        this.dateAndTime = new DateAndTime(this);
        this.demographic = new Demographic(this);
        this.dog = new Dog(this);
        this.educator = new Educator(this);
        this.shakespeare = new Shakespeare(this);
        this.slackEmoji = new SlackEmoji(this);
        this.space = new Space(this);
        this.superhero = new Superhero(this);
        this.team = new Team(this);
        this.bool = new Bool(this);
        this.beer = new Beer(this);
        this.university = new University(this);
        this.cat = new Cat(this);
        this.stock = new Stock(this);
        this.lordOfTheRings = new LordOfTheRings(this);
        this.zelda = new Zelda(this);
        this.harryPotter = new HarryPotter(this);
        this.rockBand = new RockBand(this);
        this.esports = new Esports(this);
        this.friends = new Friends(this);
        this.hipster = new Hipster(this);
        this.job = new Job(this);
        this.twinPeaks = new TwinPeaks(this);
        this.rickAndMorty = new RickAndMorty(this);
        this.yoda = new Yoda(this);
        this.matz = new Matz(this);
        this.witcher = new Witcher(this);
        this.dragonBall = new DragonBall(this);
        this.funnyName = new FunnyName(this);
        this.hitchhikersGuideToTheGalaxy = new HitchhikersGuideToTheGalaxy(this);
        this.hobbit = new Hobbit(this);
        this.howIMetYourMother = new HowIMetYourMother(this);
        this.leagueOfLegends = new LeagueOfLegends(this);
        this.overwatch = new Overwatch(this);
        this.robin = new Robin(this);
        this.starTrek = new StarTrek(this);
        this.weather = new Weather(this);
        this.lebowski = new Lebowski(this);
        this.medical = new Medical(this);
        this.country = new Country(this);
        this.animal = new Animal(this);
        this.backToTheFuture = new BackToTheFuture(this);
        this.princessBride = new PrincessBride(this);
        this.buffy = new Buffy(this);
        this.relationships = new Relationships(this);
        this.nation = new Nation(this);
        this.dune = new Dune(this);
    }

    /**
     * Constructs Faker instance with default argument.
     *
     * @return {@link Faker#Faker()}
     */
    public static Faker instance() {
        return new Faker();
    }

    /**
     * Constructs Faker instance with provided {@link Locale}.
     *
     * @param locale - {@link Locale}
     * @return {@link Faker#Faker(Locale)}
     */
    public static Faker instance(Locale locale) {
        return new Faker(locale);
    }

    /**
     * Constructs Faker instance with provided {@link Random}.
     *
     * @param random - {@link Random}
     * @return {@link Faker#Faker(Random)}
     */
    public static Faker instance(Random random) {
        return new Faker(random);
    }

    /**
     * Constructs Faker instance with provided {@link Locale} and {@link Random}.
     *
     * @param locale - {@link Locale}
     * @param random - {@link Random}
     * @return {@link Faker#Faker(Locale, Random)}
     */
    public static Faker instance(Locale locale, Random random) {
        return new Faker(locale, random);
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

    public RandomService random() {
        return this.randomService;
    }

    public Currency currency() {
        return currency;

    }

    FakeValuesService fakeValuesService() {
        return this.fakeValuesService;
    }

    public Ancient ancient() {
        return ancient;
    }

    public App app() {
        return app;
    }

    public Artist artist() {
        return artist;
    }

    public Avatar avatar() {
        return avatar;
    }

    public Aviation aviation() {
        return aviation;
    }

    public Music music() {
        return music;
    }

    public Name name() {
        return name;
    }

    public Number number() {
        return number;
    }

    public Internet internet() {
        return internet;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Pokemon pokemon() {
        return pokemon;
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

    public Buffy buffy() {
        return buffy;
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

    public File file() {
        return file;
    }

    public Finance finance() {
        return finance;
    }

    public Food food() {
        return food;
    }

    public ElderScrolls elderScrolls() {
        return elderScrolls;
    }

    public GameOfThrones gameOfThrones() {
        return gameOfThrones;
    }

    public DateAndTime date() {
        return dateAndTime;
    }

    public Demographic demographic() {
        return demographic;
    }

    public Dog dog() {
        return dog;
    }

    public Educator educator() {
        return educator;
    }

    public SlackEmoji slackEmoji() {
        return slackEmoji;
    }

    public Shakespeare shakespeare() {
        return shakespeare;
    }

    public Space space() {
        return space;
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

    public Stock stock() {
        return stock;
    }

    public LordOfTheRings lordOfTheRings() {
        return lordOfTheRings;
    }

    public Zelda zelda() {
        return zelda;
    }

    public HarryPotter harryPotter() {
        return harryPotter;
    }

    public RockBand rockBand() {
        return rockBand;
    }

    public Esports esports() {
        return esports;
    }

    public Friends friends() {
        return friends;
    }

    public Hipster hipster() {
        return hipster;
    }

    public Job job() {
        return job;
    }

    public TwinPeaks twinPeaks() {
        return twinPeaks;
    }

    public RickAndMorty rickAndMorty() {
        return rickAndMorty;
    }

    public Yoda yoda() {
        return yoda;
    }

    public Matz matz() {
        return matz;
    }

    public Witcher witcher() {
        return witcher;
    }

    public DragonBall dragonBall() {
        return dragonBall;
    }

    public FunnyName funnyName() {
        return funnyName;
    }

    public HitchhikersGuideToTheGalaxy hitchhikersGuideToTheGalaxy() {
        return hitchhikersGuideToTheGalaxy;
    }

    public Hobbit hobbit() {
        return hobbit;
    }

    public HowIMetYourMother howIMetYourMother() {
        return howIMetYourMother;
    }

    public LeagueOfLegends leagueOfLegends() {
        return leagueOfLegends;
    }

    public Overwatch overwatch() {
        return overwatch;
    }

    public Robin robin() {
        return robin;
    }

    public StarTrek starTrek() {
        return starTrek;
    }

    public Weather weather() {
        return weather;
    }

    public Lebowski lebowski() {
        return lebowski;
    }

    public Medical medical(){return medical;}

    public Country country(){ return country;}

    public Animal animal(){ return animal; }

    public BackToTheFuture backToTheFuture() {
        return  backToTheFuture;
    }

    public PrincessBride princessBride() {
        return princessBride;
    }

    public Relationships relationships() {
        return relationships;
    }

    public Nation nation() {
        return nation;
    }

    public Dune dune() {
        return dune;
    }
    
    public String resolve(String key) {
        return this.fakeValuesService.resolve(key, this, this);
    }

    /**
     * Allows the evaluation of native YML expressions to allow you to build your own.
     * <p>
     * The following are valid expressions:
     * <ul>
     * <li>#{regexify '(a|b){2,3}'}</li>
     * <li>#{regexify '\\.\\*\\?\\+'}</li>
     * <li>#{bothify '????','false'}</li>
     * <li>#{Name.first_name} #{Name.first_name} #{Name.last_name}</li>
     * <li>#{number.number_between '1','10'}</li>
     * </ul>
     *
     * @param expression (see examples above)
     * @return the evaluated string expression
     * @throws RuntimeException if unable to evaluate the expression
     */
    public String expression(String expression) {
        return this.fakeValuesService.expression(expression, this);
    }
}
