package com.github.javafaker;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AbstractFakerTest {
    @Spy
    protected Faker faker;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        rootLogger.setLevel(Level.FINEST);
        for (Handler h : handlers) {
            h.setLevel(Level.FINEST);
        }
    }

}
