package com.github.javafaker;

class PasswordExample {
    public static void main(String args[]) {
        System.out.println("Hello World");
        Faker faker = new Faker();

        // char[] specialBasic = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
        // for (int i=0; i<10; i++) {
        //     String pw = faker.internet().password(10, 20, true, specialBasic, true);
        //     System.out.println(pw);
        // }

        char[] specialLarge = new char[]{'(',')','+',',','-','.','/',':',';','<','=','>','?','[',']','_','`','{','|','}','~','\"', '\'', '\\','!', '@', '#', '$', '%', '^', '&', '*'};
        for (int i=0; i<10; i++) {
            String pw = faker.internet().password(10, 20, true, specialLarge, true);
            System.out.println(pw);
        }                              
    }
}
