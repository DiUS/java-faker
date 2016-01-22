package com.github.javafaker;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.Locale;

/**
 * @see <a href="https://gist.github.com/digulla/5884162">https://gist.github.com/digulla/5884162</a>
 */
public class DefaultLocaleRule extends TestWatcher {

    private Locale originalDefault;
    private Locale currentDefault;

    public DefaultLocaleRule() {
        this( null );
    }

    public DefaultLocaleRule( Locale defaultForTests ) {
        currentDefault = defaultForTests;
    }

    @Override
    protected void starting( Description description ) {
        originalDefault = Locale.getDefault();

        if( null != currentDefault ) {
            Locale.setDefault( currentDefault );
        }
    }

    @Override
    protected void finished( Description description ) {
        Locale.setDefault( originalDefault );
    }

    public void setDefault( Locale locale ) {
        if( null == locale ) {
            locale = originalDefault;
        }

        Locale.setDefault( locale );
    }


    public static DefaultLocaleRule root() {
        return new DefaultLocaleRule( Locale.ROOT );
    }

    public static DefaultLocaleRule en() {
        return new DefaultLocaleRule( Locale.ENGLISH );
    }

    public static DefaultLocaleRule de() {
        return new DefaultLocaleRule( Locale.GERMAN );
    }

    public static DefaultLocaleRule fr() {
        return new DefaultLocaleRule( Locale.FRENCH );
    }
}
