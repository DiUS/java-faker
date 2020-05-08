package com.github.javafaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public final class LastNameTest {
    private LastNameTest(){

    }

    public static void main(String args[]){
        Logger logger = LoggerFactory.getLogger(LastNameTest.class);
        Faker faker=new Faker(new Locale("es"));

        while(true){
            String lastname1=faker.name().lastName();
            //System.out.println(lastname+" old");
            if(lastname1.equals("Ibañez")){
                logger.info(lastname1+" new 1");
                break;
            }
        }

        while(true){
            String lastname2=faker.name().lastName();
            //System.out.println(lastname+" old");
            if(lastname2.equals("De Romaña")){
                logger.info(lastname2+" new 2");
                break;
            }
        }

        while(true){
            String lastname3=faker.name().lastName();
            //System.out.println(lastname+" old");
            if(lastname3.equals("Ormeño")){
               logger.info(lastname3+" new 3");
                break;
            }
        }
    }
}
