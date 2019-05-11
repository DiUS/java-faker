package com.github.javafaker;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AbstractFakerTest {

    @Spy
    protected Faker faker;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);

        Logger rootLogger = LogManager.getLogManager().getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        rootLogger.setLevel(Level.INFO);
        for (Handler h : handlers) {
            h.setLevel(Level.INFO);
        }
    }

}
