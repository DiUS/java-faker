package com.github.javafaker.service;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

public class FakeValues implements FakeValuesInterface {
    private final Locale locale;
    private final String filename;
    private final String path;
    private Map values;

    public FakeValues(Locale locale, String filename, String path) {
        this.locale = locale;
        this.filename = filename;
        this.path = path;
    }

    @Override
    public Map get(String key) {
        if (values == null) {
            values = loadValues();
        }

        return values == null ? null : (Map) values.get(key);
    }

    private Map loadValues() {
        String path = "/" + locale.getLanguage() + "/" + this.filename;
        InputStream stream = findStream(path);
        if (stream == null) {
            String pathWithFilename = "/" + filename + ".yml";
            stream = findStream(pathWithFilename);
            if (stream == null) {
                String pathWithLocale = "/" + locale.getLanguage() + ".yml";
                stream = findStream(pathWithLocale);
                if (stream == null) {
                    // TODO
                    return null;
                }
            }
        }

        final Map valuesMap = new Yaml().loadAs(stream, Map.class);
        Map localeBased = (Map) valuesMap.get(locale.getLanguage());
        if (localeBased == null) {
            localeBased = (Map) valuesMap.get(filename);
        }
        return (Map) localeBased.get("faker");
    }

    private InputStream findStream(String filename) {
        InputStream streamOnClass = getClass().getResourceAsStream(filename);
        if (streamOnClass != null) {
            return streamOnClass;
        }
        return getClass().getClassLoader().getResourceAsStream(filename);
    }

    public boolean supportsPath(String path) {
        return this.path.equals(path);
    }
}
