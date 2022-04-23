package com.github.javafaker;

/**
 * @author zhou mintao
 * CS304 issue link:https://github.com/DiUS/java-faker/issues/715
 * A class for generating random value of weapon
 */

public class Weapon {
    private final Faker faker;

    protected Weapon(final Faker faker){
        this.faker = faker;
    }

    /**
     * @return a random value of weapon name
     */
    public String getWeapon(){
        String type = faker.resolve("weapon.type");
        System.out.println(type);
        String types = faker.resolve("weapon.".concat(type));
        System.out.println(types);
        String res = faker.resolve("weapon.".concat(types));
        System.out.println(res);
        return res;
    }

    /**
     * @return a random value of provenance
     */
    public String provenance(){
        String pro = faker.resolve("weapon.provenance");
        System.out.println(pro);
        return pro;
    }

    /**
     * @return a random value of user
     */
    public String user() {
        String user = faker.resolve("weapon.user");
        System.out.println(user);
        return user;
    }

    /**
     * @return a random value of function
     */
    public String function(){
        String func = faker.resolve("weapon.function");
        System.out.println(func);
        return func;
    }

    /**
     * @return a random value of target
     */
    public String target() {
        String tar = faker.resolve("weapon.target");
        System.out.println(tar);
        return tar;
    }

    /**
     * @return a random value of type
     */
    public String type(){
        String type = faker.resolve("weapon.type");
        System.out.println(type);
        return type;
    }

}
