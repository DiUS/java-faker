package javafaker;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameFakerTest {
    private static final Logger logger = LoggerFactory.getLogger(NameFakerTest.class);

    @Test
    public void shouldFetchCorrectName() {
        String firstName = NameFaker.firstName();
        logger.info("Test first name: " + firstName);
        Assert.assertNotNull(firstName);

        String lastName = NameFaker.lastName();
        logger.info("Test last name: " + lastName);
        Assert.assertNotNull(lastName);

        String prefix = NameFaker.prefix();
        logger.info("Test prefix: " + prefix);
        Assert.assertNotNull(prefix);

        String suffix = NameFaker.suffix();
        logger.info("Test suffix: " + suffix);
        Assert.assertNotNull(suffix);

        String name = NameFaker.name();
        logger.info("Test name: " + name);
        Assert.assertNotNull(name);
    }
}
