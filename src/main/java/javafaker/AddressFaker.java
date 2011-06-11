package javafaker;

import static javafaker.Faker.bothify;
import static javafaker.Faker.fetch;
import static javafaker.Faker.numerify;
import static javafaker.NameFaker.firstName;
import static javafaker.NameFaker.lastName;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

import java.util.ArrayList;
import java.util.List;

public class AddressFaker {
    public static String streetName() {
        List<String> possibleStreetNames = new ArrayList<String>();
        possibleStreetNames.add(join(new Object[] { lastName(), streetSuffix() }, " "));
        possibleStreetNames.add(join(new Object[] { firstName(), streetSuffix() }, " "));
        return possibleStreetNames.get(nextInt(possibleStreetNames.size()));
    }

    public static String streetAddress(boolean includeSecondary) {
        String streetAddress = (String) fetch("address.street_address") + " " + streetName();
        if (includeSecondary) {
            streetAddress = streetAddress + " " + secondaryAddress();
        }
        return numerify(streetAddress);
    }

    public static String secondaryAddress() {
        return numerify((String) fetch("address.secondary_address"));
    }

    public static String zipCode() {
        return bothify((String) fetch("address.postcode"));
    }

    public static String streetSuffix() {
        return (String) fetch("address.street_suffix");
    }

    public static String citySuffix() {
        return (String) fetch("address.city_suffix");
    }

    public static String cityPrefix() {
        return (String) fetch("address.city_prefix");
    }

    public static String stateAbbr() {
        return (String) fetch("address.state_abbr");
    }

    public static String country() {
        return (String) fetch("address.country");
    }
}
