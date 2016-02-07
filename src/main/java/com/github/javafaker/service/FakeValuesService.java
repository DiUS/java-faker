package com.github.javafaker.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FakeValuesService {
    private static final char[] METHOD_NAME_DELIMITERS = {'_'};
    private final Map<String, Object> fakeValuesMap;
    private final RandomService randomService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public FakeValuesService(Locale locale, RandomService randomService) {
        String languageCode = locale.getLanguage();
        final InputStream stream = findStream("/" + languageCode + ".yml");
        if (stream == null) {
            throw new LocaleDoesNotExistException(String.format("%s could not be found, does not have a corresponding yaml file", locale));
        }
        Map rootMap;
        try {
            rootMap = (Map) new Yaml().load(stream);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                throw new IllegalStateException("Unable close a stream", e);
            }
        }
        Map valuesMap = (Map) rootMap.get(languageCode);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
        this.randomService = randomService;
    }

    private InputStream findStream(String filename) {
        InputStream streamOnClass = getClass().getResourceAsStream(filename);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        return getClass().getClassLoader().getResourceAsStream(filename);
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
     * A property that is composed of other properties.
     * <p/>
     * It firstly fetches the formatKey using {@link #fetch(String)}. It will
     * proceed to convert the returned properties from the {@link #fetch(String)}
     * method to a methodName and invoke this method against the object passed in.
     * Finally, concatenation occurs with the return values of the methods
     * using the joiner parameter as a separator.
     *
     * @param formatKey
     * @param joiner
     * @param objectToInvokeMethodOn
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String composite(String formatKey, String joiner, Object objectToInvokeMethodOn) {
        List<String> format = (List<String>) fetch(formatKey);

        String[] parts = new String[format.size()];
        for (int i = 0; i < parts.length; i++) {
            // remove leading colon
            String methodName = format.get(i).substring(1);
            // convert to camel case
            methodName = WordUtils.capitalizeFully(methodName, METHOD_NAME_DELIMITERS).replaceAll("_", "");
            methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

            try {
                parts[i] = (String) objectToInvokeMethodOn.getClass().getMethod(methodName, (Class[]) null).invoke(objectToInvokeMethodOn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return StringUtils.join(parts, joiner);
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

    private int nextInt(int n) {
        return randomService.nextInt(n);
    }
}
