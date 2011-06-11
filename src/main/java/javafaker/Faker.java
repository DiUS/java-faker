package javafaker;

import java.util.List;
import java.util.Locale;
import java.util.Map;

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
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Faker {
    private static final Logger logger = LoggerFactory.getLogger(Faker.class);
    public static final Locale LOCALE = Locale.ENGLISH;

    private static Map<String, Object> fakeValuesMap;

    static {
        String languageCode = LOCALE.getLanguage();
        Map valuesMap = (Map) Yaml.load(ClassLoader.getSystemResourceAsStream(languageCode + ".yml"));
        valuesMap = (Map) valuesMap.get(languageCode);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
    }

    public static String numerify(String numberString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '#') {
                sb.append(RandomUtils.nextInt(10));
            } else {
                sb.append(numberString.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String letterify(String letterString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < letterString.length(); i++) {
            if (letterString.charAt(i) == '?') {
                sb.append((char) (97 + RandomUtils.nextInt(26))); // a-z
            } else {
                sb.append(letterString.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String bothify(String string) {
        return letterify(numerify(string));
    }

    /**
     * Fetch a random value from an array item specified by the key
     *
     * @param key
     * @return
     */
    public static Object fetch(String key) {
        List valuesArray = (List) fetchObject(key);
        return valuesArray.get(RandomUtils.nextInt(valuesArray.size()));
    }

    /**
     * Return the object selected by the key from yaml file.
     *
     * @param key
     *            key contains path to an object. Path segment is separated by
     *            dot. E.g. name.first_name
     * @return
     */
    public static Object fetchObject(String key) {
        String[] path = key.split("\\.");
        Object currentValue = fakeValuesMap;
        for (String pathSection : path) {
            currentValue = ((Map<String, Object>) currentValue).get(pathSection);
        }
        return currentValue;
    }
}
