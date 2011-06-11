package javafaker;

import org.junit.Assert;
import org.junit.Test;

public class FakerTest {
    @Test
    public void shouldFetchCorrectValue() {
        String firstName = Faker.firstName();
        Assert.assertNotNull(firstName);
        String lastName = Faker.lastName();
        Assert.assertNotNull(lastName);
        String prefix = Faker.prefix();
        Assert.assertNotNull(prefix);
        String phoneNumber = Faker.phoneNumber();
        Assert.assertNotNull(phoneNumber);
    }
}
