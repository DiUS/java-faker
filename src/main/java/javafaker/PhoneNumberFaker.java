package javafaker;

import static javafaker.Faker.fetch;
import static javafaker.Faker.numerify;

public class PhoneNumberFaker {

    public static String phoneNumber() {
        return numerify((String) fetch("phone_number.formats"));
    }
}
