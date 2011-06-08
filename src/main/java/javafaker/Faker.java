package javafaker;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.ho.yaml.Yaml;

public class Faker {
    public static final Locale LOCALE = Locale.ENGLISH;

    private final Map<String, Object> fakeValuesMap;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Faker() {
        String languageCode = LOCALE.getLanguage();
        Map valuesMap = (Map) Yaml.load(ClassLoader.getSystemResourceAsStream(languageCode + ".yml"));
        valuesMap = (Map) valuesMap.get(languageCode);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
    }

    public String firstName() {
        return fetch("name.first_name");
    }

    public String lastName() {
        return fetch("name.last_name");
    }

    public String prefix() {
        return fetch("name.prefix");
    }

    public String phoneNumber() {
        return numerify(fetch("phone_number.formats"));
    }

    public String numerify(String numberString) {
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

    @SuppressWarnings("unchecked")
    public String fetch(String key) {
        String[] path = key.split("\\.");
        Object currentValue = fakeValuesMap;
        for (String pathSection : path) {
            currentValue = ((Map<String, Object>) currentValue).get(pathSection);
        }
        List<String> valuesArray = (List<String>) currentValue;
        return valuesArray.get(RandomUtils.nextInt(valuesArray.size()));
    }
}
