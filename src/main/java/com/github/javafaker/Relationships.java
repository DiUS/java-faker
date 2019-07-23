package com.github.javafaker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Relationships {
    private final Faker faker;

    protected Relationships(final Faker faker) {
        this.faker = faker;
    }

    public String direct() {
        return faker.resolve("relationship.familial.direct");
    }
    
    public String extended() {
        return faker.resolve("relationship.familial.extended");
    }
    
    public String in_law() {
        return faker.resolve("relationship.in_law");
    }

    public String spouse() {
        return faker.resolve("relationship.spouse");
    }
    
    public String parent() {
        return faker.resolve("relationship.parent");
    }
    
    public String sibling() {
        return faker.resolve("relationship.sibling");
    }
    
    public String any() {
        String[] relationships = new String[] {"direct", "extended", "in_law", "spouse", 
                "parent", "sibling"};
        int idx = faker.random().nextInt(relationships.length);
        String methodName = relationships[idx];
        try {
            Method m = Relationships.class.getMethod(methodName);
            Relationships relInstance = new Relationships(faker);
            return (String)m.invoke(relInstance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}