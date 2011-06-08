package javafaker;

import org.junit.Assert;
import org.junit.Test;

public class FakerTest {
    @Test
    public void shouldFetchCorrectValue() {
        Faker faker = new Faker();
        String firstName = faker.firstName();
        Assert.assertNotNull(firstName);
        String lastName = faker.lastName();
        Assert.assertNotNull(lastName);
        String prefix = faker.prefix();
        Assert.assertNotNull(prefix);
        String phoneNumber = faker.phoneNumber();
        Assert.assertNotNull(phoneNumber);
    }
}
