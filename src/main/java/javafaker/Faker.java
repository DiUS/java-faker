package javafaker;

import static javafaker.Faker.fetch;
import static javafaker.Faker.numerify;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.ho.yaml.Yaml;

/**
 * Provides utility methods for generating fake strings, such as names, phone
 * numbers, addresses. generate random strings with given patterns
 *
 * @author ren
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Faker {
    public static final Locale LOCALE = Locale.ENGLISH;

    private static Map<String, Object> fakeValuesMap;

    static {
        String languageCode = LOCALE.getLanguage();
        Map valuesMap = (Map) Yaml.load(ClassLoader.getSystemResourceAsStream(languageCode + ".yml"));
        valuesMap = (Map) valuesMap.get(languageCode);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
    }

    private static final char[] METHOD_NAME_DELIMITERS = { '_' };

    public static String name() {
        List<String> nameFormat = (List<String>) fetch("name.formats");

        String[] nameParts = new String[nameFormat.size()];
        for (int i = 0; i < nameParts.length; i++) {
            // remove leading colon
            String methodName = nameFormat.get(i).substring(1);
            // convert to camel case
            methodName = WordUtils.capitalizeFully(methodName, METHOD_NAME_DELIMITERS).replaceAll("_", "");
            methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

            try {
                nameParts[i] = (String) Faker.class.getDeclaredMethod(methodName, (Class[]) null).invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return StringUtils.join(nameParts, " ");
    }

    public static String firstName() {
        return (String) fetch("name.first_name");
    }

    public static String lastName() {
        return (String) fetch("name.last_name");
    }

    public static String prefix() {
        return (String) fetch("name.prefix");
    }

    public static String suffix() {
        return (String) fetch("name.suffix");
    }

    public static String phoneNumber() {
        return numerify((String) fetch("phone_number.formats"));
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

    public static Object fetch(String key) {
        String[] path = key.split("\\.");
        Object currentValue = fakeValuesMap;
        for (String pathSection : path) {
            currentValue = ((Map<String, Object>) currentValue).get(pathSection);
        }
        List valuesArray = (List) currentValue;
        return valuesArray.get(RandomUtils.nextInt(valuesArray.size()));
    }
}
