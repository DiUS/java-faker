package javafaker;

import static javafaker.Faker.fetch;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

public class NameFaker {
    private static final char[] METHOD_NAME_DELIMITERS = { '_' };

    public static String name() {
        @SuppressWarnings("unchecked")
        List<String> nameFormat = (List<String>) fetch("name.formats");

        String[] nameParts = new String[nameFormat.size()];
        for (int i = 0; i < nameParts.length; i++) {
            // remove leading colon
            String methodName = nameFormat.get(i).substring(1);
            // convert to camel case
            methodName = WordUtils.capitalizeFully(methodName, METHOD_NAME_DELIMITERS).replaceAll("_", "");
            methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

            try {
                nameParts[i] = (String) NameFaker.class.getDeclaredMethod(methodName, (Class[]) null).invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        String name = StringUtils.join(nameParts, " ");
        return name;
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
}
