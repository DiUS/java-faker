package com.github.javafaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
public class CryptoTest extends AbstractFakerTest {
    @Test
    public void testMd5() {
        assertThat(faker.crypto().md5(), matchesRegularExpression("[a-z\\d]+"));
    }

    @Test
    public void testSha1() {
        assertThat(faker.crypto().sha1(), matchesRegularExpression("[a-z\\d]+"));
    }

    @Test
    public void testSha256() {
        assertThat(faker.crypto().sha256(), matchesRegularExpression("[a-z\\d]+"));
    }

    @Test
    public void testSha512() {
        assertThat(faker.crypto().sha512(), matchesRegularExpression("[a-z\\d]+"));
    }
    
    
    // this is a total corner case, but if for some reason a system doesn't support
    // a specific algorithm then it will wrap the NoSuchAlgorithm in a RuntimeException
    // and throw it.  This should never happen, but who knows.
    @Test(expected = RuntimeException.class)
    @PrepareForTest(Crypto.class)
    public void missingAlgorithVomitsHorribly() throws NoSuchAlgorithmException {
        mockStatic(MessageDigest.class);
        
        PowerMockito.when(MessageDigest.getInstance(Mockito.anyString()))
                .thenThrow(new NoSuchAlgorithmException("I no haz that algorithm."));
        
        try {
            faker.crypto().sha1();
        } catch (RuntimeException re) {
            assertThat(re.getCause(), is(instanceOf(NoSuchAlgorithmException.class)));
            assertThat(re.getCause().getMessage(), is("I no haz that algorithm."));
            throw re;
        }
    }
}
