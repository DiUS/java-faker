package com.github.javafaker;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AvatarTest extends AbstractFakerTest {

    @Test
    public void testAvatar() {
        UrlValidator urlValidator = new UrlValidator();
        assertTrue(urlValidator.isValid(faker.avatar().image()));
    }
}
