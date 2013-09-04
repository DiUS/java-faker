package com.github.javafaker;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.ho.yaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides utility methods for generating fake strings, such as names, phone
 * numbers, addresses. generate random strings with given patterns
 *
 * @author ren
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Faker {

    private static final Logger logger = LoggerFactory.getLogger(Faker.class);
    private static final char[] METHOD_NAME_DELIMITERS = {'_'};
    private Map<String, Object> fakeValuesMap;
    private static final Transformer letterTransformer = new Transformer(new LetterTransformer());
    private static final Transformer numberTransformer = new Transformer(new NumberTransformer());
    private static final Transformer letterNumberTransformer = new Transformer(new LetterTransformer(), new NumberTransformer());

    public Faker() {
        this(Locale.ENGLISH);
    }

    public Faker(Locale locale) {
        logger.info("Using default locale " + locale);
        String languageCode = locale.getLanguage();
        Map valuesMap = (Map) Yaml.load(getClass().getClassLoader().getResourceAsStream(languageCode + ".yml"));
        valuesMap = (Map) valuesMap.get(languageCode);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
    }

    /**
     * Converts a formatted string containing {@code #} characters to random 0-9
     * characters.
     *
     * All other characters will remain unmodified.
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        return numberTransformer.transform(numberString);
    }

    /**
     * Converts a formatted string containing {@code ?} characters to random a-z
     * characters.
     *
     * All other characters will remain unmodified.
     *
     * @param letterString
     * @return
     */
    public String letterify(String letterString) {
        return letterTransformer.transform(letterString);
    }

    /**
     * Converts a formatted string containing either {@code #} or {@code ?},
     * changing the characters into 0-9 or a-z, respectively.
     *
     * All other characters will remain unmodified.
     *
     * @param string
     * @return
     */
    public String bothify(String string) {
        return letterNumberTransformer.transform(string);
    }

    /**
     * Fetch a random value from an array item specified by the key
     *
     * @param key
     * @return
     */
    public Object fetch(String key) {
        List valuesArray = (List) fetchObject(key);
        return valuesArray.get(RandomUtils.nextInt(valuesArray.size()));
    }

    public String fetchString(String key) {
        return (String) fetch(key);
    }

    /**
     * Return the object selected by the key from yaml file.
     *
     * @param key key contains path to an object. Path segment is separated by
     * dot. E.g. name.first_name
     * @return
     */
    public Object fetchObject(String key) {
        String[] path = key.split("\\.");
        Object currentValue = fakeValuesMap;
        for (String pathSection : path) {
            currentValue = ((Map<String, Object>) currentValue).get(pathSection);
        }
        return currentValue;
    }

    public String name() {
        List<String> nameFormat = (List<String>) fetch("name.formats");

        String[] nameParts = new String[nameFormat.size()];
        for (int i = 0; i < nameParts.length; i++) {
            // remove leading colon
            String methodName = nameFormat.get(i).substring(1);
            // convert to camel case
            methodName = WordUtils.capitalizeFully(methodName, METHOD_NAME_DELIMITERS).replaceAll("_", "");
            methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

            try {
                nameParts[i] = (String) Faker.class.getDeclaredMethod(methodName, (Class[]) null).invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        String name = StringUtils.join(nameParts, " ");
        return name;
    }

    public String firstName() {
        return fetchString("name.first_name");
    }

    public String lastName() {
        return fetchString("name.last_name");
    }

    public String prefix() {
        return fetchString("name.prefix");
    }

    public String suffix() {
        return fetchString("name.suffix");
    }

    public String phoneNumber() {
        return numerify(fetchString("phone_number.formats"));
    }

    // lorem
    public List<String> words(int num) {
        List<String> words = (List<String>) fetchObject("lorem.words");
        Collections.shuffle(words);
        return words.subList(0, num);
    }

    public List<String> words() {
        return words(3);
    }

    public String sentence(int wordCount) {
        return capitalize(join(words(wordCount + RandomUtils.nextInt(6)), " ") + ".");
    }

    public String sentence() {
        return sentence(3);
    }

    public List<String> sentences(int sentenceCount) {
        List<String> sentences = new ArrayList<String>(sentenceCount);
        for (int i = 0; i < sentenceCount; i++) {
            sentences.add(sentence());
        }
        return sentences;
    }

    public String paragraph(int sentenceCount) {
        return join(sentences(sentenceCount + nextInt(3)), " ");
    }

    public String paragraph() {
        return paragraph(3);
    }

    public List<String> paragraphs(int paragraphCount) {
        List<String> paragraphs = new ArrayList<String>(paragraphCount);
        for (int i = 0; i < paragraphCount; i++) {
            paragraphs.add(paragraph());
        }
        return paragraphs;
    }

    // address
    public String streetName() {
        List<String> possibleStreetNames = new ArrayList<String>();
        possibleStreetNames.add(join(new Object[]{lastName(), streetSuffix()}, " "));
        possibleStreetNames.add(join(new Object[]{firstName(), streetSuffix()}, " "));
        return possibleStreetNames.get(nextInt(possibleStreetNames.size()));
    }

    public String streetAddress(boolean includeSecondary) {
        String streetAddress = fetchString("address.street_address") + " " + streetName();
        if (includeSecondary) {
            streetAddress = streetAddress + " " + secondaryAddress();
        }
        return numerify(streetAddress);
    }

    public String secondaryAddress() {
        return numerify(fetchString("address.secondary_address"));
    }

    public String zipCode() {
        return bothify(fetchString("address.postcode"));
    }

    public String streetSuffix() {
        return fetchString("address.street_suffix");
    }

    public String citySuffix() {
        return fetchString("address.city_suffix");
    }

    public String cityPrefix() {
        return fetchString("address.city_prefix");
    }

    public String stateAbbr() {
        return fetchString("address.state_abbr");
    }

    public String country() {
        return fetchString("address.country");
    }

    private static class Transformer {

        private final CharacterTransformer[] transformers;

        public Transformer(CharacterTransformer... transformers) {
            this.transformers = transformers;
        }

        /**
         * Transforms the source string into a new string, using the
         * transformers with which this instance was initialized
         *
         * This method is tread safe
         *
         * @param source
         * @return
         */
        public String transform(String source) {
            StringBuilder sb = new StringBuilder();
            charLoop:
            for (int i = 0; i < source.length(); i++) {
                char c = source.charAt(i);
                for (CharacterTransformer t : transformers) {
                    if (t.triggerCharacter() == c) {
                        sb.append(t.transform(c));
                        break charLoop;
                    }
                }
                //if we're here, the char didn't match any transformer
                sb.append(source.charAt(i));
            }

            return sb.toString();
        }
    }

    private static interface CharacterTransformer {

        char triggerCharacter();

        char transform(char source);
    }

    private static class LetterTransformer implements CharacterTransformer {

        public char triggerCharacter() {
            return '?';
        }

        public char transform(char source) {
            return (char) (97 + RandomUtils.nextInt(26)); // a-z
        }
    }

    private static class NumberTransformer implements CharacterTransformer {

        public char triggerCharacter() {
            return '#';
        }

        public char transform(char source) {
            return (char) RandomUtils.nextInt(10);
        }
    }
}
