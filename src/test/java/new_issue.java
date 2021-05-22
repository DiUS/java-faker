import com.github.javafaker.Faker;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class new_issue {

    public static void main(String[] args) {

        Faker faker = new Faker();
        System.out.println(faker.hearthstone().mainCharacter());

//        System.out.println(faker.name().name());
//        System.out.println(faker.university().name());
//        System.out.println(faker.idNumber().valid());
//        System.out.println(faker.phoneNumber().cellPhone());
//
//        faker = new Faker(new Locale("zh-CN"));
//        System.out.println(faker.name().name());
//        System.out.println(faker.university().name());
//        System.out.println(faker.idNumber().valid());
//        System.out.println(faker.phoneNumber().phoneNumber());

    }

    public static void runIssue587(Faker f){
        f.company().name();
    }

}
