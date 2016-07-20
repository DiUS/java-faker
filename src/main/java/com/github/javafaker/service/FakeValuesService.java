package com.github.javafaker.service;

import com.github.javafaker.Resolver;
import com.mifmif.common.regex.Generex;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FakeValuesService implements FakeValuesServiceInterface {
    private static final char[] METHOD_NAME_DELIMITERS = {'_'};
    private final Map<String, Object> fakeValuesMap;
    private final RandomService randomService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public FakeValuesService(Locale locale, RandomService randomService) {
        String filename = locale.getLanguage();
        InputStream stream = findStream(filename);
        if (stream == null) {
            filename = filename + "-" + locale.getCountry();
            stream = findStream(filename);
        }
        if (stream == null) {
            throw new LocaleDoesNotExistException(String.format("%s could not be found, does not have a corresponding yaml file", locale));
        }

        Map valuesMap = (Map) new Yaml().load(stream);
        valuesMap = (Map) valuesMap.get(filename);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
        this.randomService = randomService;
    }

    private InputStream findStream(String filename) {
        String filenameWithExtension =  "/" + filename + ".yml";
        InputStream streamOnClass = getClass().getResourceAsStream(filenameWithExtension);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        return getClass().getClassLoader().getResourceAsStream(filenameWithExtension);
    }

    /**
     * Fetch a random value from an array item specified by the key
     *
     * @param key
     * @return
     */
    public Object fetch(String key) {
        List valuesArray = (List) fetchObject(key);
        return valuesArray.get(nextInt(valuesArray.size()));
    }

    /**
     * Same as {@link #fetch(String)} except this casts the result into a String.
     *
     * @param key
     * @return
     */
    public String fetchString(String key) {
        return (String) fetch(key);
    }

    /**
     * Safely fetches a key.
     *
     * If the value is null, it will return an empty string.
     *
     * If it is a list, it will assume it is a list of strings and select a random value from it.
     *
     * Otherwise it will just return the value as a string.
     *
     * @param key
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String safeFetch(String key) {
        Object o = fetchObject(key);
        if (o == null) return "";
        if (o instanceof List) {
            List<String> values = (List<String>) o;
            return values.get(randomService.nextInt(values.size()));
        } else {
            return (String) o;
        }
    }

    /**
     * Return the object selected by the key from yaml file.
     *
     * @param key key contains path to an object. Path segment is separated by
     *            dot. E.g. name.first_name
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object fetchObject(String key) {
        String[] path = key.split("\\.");
        Object currentValue = fakeValuesMap;
        for (String pathSection : path) {
            currentValue = ((Map<String, Object>) currentValue).get(pathSection);
        }
        return currentValue;
    }

    /**
     * Returns a string with the '#' characters in the parameter replaced with random digits between 0-9 inclusive.
     * <p/>
     * For example, the string "ABC##EFG" could be replaced with a string like "ABC99EFG".
     *
     * @param numberString
     * @return
     */
    public String numerify(String numberString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '#') {
                sb.append(nextInt(10));
            } else {
                sb.append(numberString.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String)}
     * over the incoming string.
     *
     * @param string
     * @return
     */
    public String bothify(String string) {
        return letterify(numerify(string));
    }

    /**
     * Applies both a {@link #numerify(String)} and a {@link #letterify(String,boolean)}
     * over the incoming string.
     *
     * @param string
     * @param isUpper
     * @return
     */
    public String bothify(String string, boolean isUpper) {
        return letterify(numerify(string), isUpper);
    }

    /**
     * Generates a String that matches the given regular expression.
     */
    public String regexify(String regex) {
        Generex generex = new Generex(regex);
        generex.setSeed(randomService.nextLong());
        return generex.random();
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p/>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @return
     */
    public String letterify(String letterString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < letterString.length(); i++) {
            if (letterString.charAt(i) == '?') {
                sb.append((char) (97 + nextInt(26))); // a-z
            } else {
                sb.append(letterString.charAt(i));
            }
        }

        return sb.toString();
    }

    /**
     * Returns a string with the '?' characters in the parameter replaced with random alphabetic
     * characters.
     * <p/>
     * For example, the string "12??34" could be replaced with a string like "12AB34".
     *
     * @param letterString
     * @param isUpper specifies whether or not letters should be upper case
     * @return
     */
    public String letterify(String letterString, boolean isUpper) {
        StringBuffer sb = new StringBuffer();
	int baseChar = (isUpper) ? 65 : 97; // from ascii table
        for (int i = 0; i < letterString.length(); i++) {
            if (letterString.charAt(i) == '?') {
                sb.append((char) (baseChar + nextInt(26))); // a-z
            } else {
                sb.append(letterString.charAt(i));
            }
        }

        return sb.toString();
    }

    private int nextInt(int n) {
        return randomService.nextInt(n);
    }

    /**
     * Resolves a key to a method on an object.
     *
     * #{hello} with result in a method call to current.hello();
     *
     * #{Person.hello_someone} will result in a method call to person.helloSomeone();
     *
     * @param key
     * @param current
     * @param resolver
     * @return
     */
    public String resolve(String key, Object current, Resolver resolver) {
        String unresolvedString = safeFetch(key);
        String regex = "#\\{[A-Za-z_.]+\\}";
        Matcher matcher = Pattern.compile(regex).matcher(unresolvedString);
        while (matcher.find()) {
            String matched = matcher.group();
            String strippedMatched = matched.replace('#', ' ').replace('{', ' ').replace('}', ' ').trim();
            boolean isFirstLetterCapital = Character.isUpperCase(strippedMatched.charAt(0));
            String objectWithMethodToResolve = isFirstLetterCapital ? strippedMatched : current.getClass().getSimpleName() + "." + strippedMatched;
            String resolvedValue = resolver.resolve(objectWithMethodToResolve);
            unresolvedString = unresolvedString.replace(matched, resolvedValue);
        }
        return unresolvedString;
    }

}
