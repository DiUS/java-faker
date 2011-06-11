package javafaker;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneNumberFakerTest {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberFakerTest.class);

    @Test
    public void testPhoneNumber() {
        String phoneNumber = PhoneNumberFaker.phoneNumber();
        logger.info("Phone number: " + phoneNumber);
        Assert.assertNotNull(phoneNumber);
    }
}
