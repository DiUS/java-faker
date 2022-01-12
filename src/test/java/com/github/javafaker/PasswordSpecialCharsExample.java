package com.github.javafaker;

class PasswordSpecialCharsExample {
    // CS427 Issue link: https://github.com/DiUS/java-faker/issues/585
    /**
     * Example to illustrate use of generating random passwords (character strings) with inclusion of client-defined special characters
     * @param args Command-line arguments
     */
    public static void main(String args[]) {
        Faker faker = new Faker();   

        System.out.println("PASSWORDS WITH EXTENDED SET OF SPECIAL CHARACTERS");
        char[] specialLarge = new char[]{'(',')','+',',','-','.','/',':',';','<','=','>','?','[',']','_','`','{','|','}','~','\"', '\'', '\\','!', '@', '#', '$', '%', '^', '&', '*'};
        for (int i=0; i<10; i++) {
            String pw = faker.internet().password(10, 20, true, specialLarge, true);
            System.out.println(pw);
        }                              

        System.out.println("PASSWORDS WITH INTERNATIONAL CHARACTERS");
        char[] specialASCIIinternational = new char[]{'Å','á','æ','ç','É','ê','î','ï','ñ','Ø','ò','Û','û','ÿ'};
        for (int i=0; i<10; i++) {
            String pw = faker.internet().password(10, 20, true, specialASCIIinternational, true);
            System.out.println(pw);
        }

    }
}
