package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Implementation based on the definition at
 * https://en.wikipedia.org/wiki/Unique_Population_Registry_Code
 */
//CS304 Issue link: https://github.com/DiUS/java-faker/issues/571
public class EsMXIdNumber {

    private HashMap<String, String> codigoverificacion;

    private String[] palabrasnovalidas = {"BUEI", "CACA", "CAGA", "CAKA", "COGE",
            "COJE", "COJO", "FETO", "JOTO", "KACO", "KAGO", "KOJO", "KULO",
            "MAMO", "MEAS", "MION", "MULA", "PEDO", "PUTA", "QULO", "RUIN",
            "GUEY", "KACA", "KAGA", "KOGE", "KAKA", "MAME", "MEAR", "MEON",
            "MOCO", "PEDA", "PENE", "PUTO", "RATA"
    };

    private String[] nexosnovalidos = {"LA", "SA DE CV", "LOS", "Y", "SA", "CIA",
            "SOC", "COOP", "A EN P", "S EN C", "EN", "CON", "SUS", "SC", "SCS",
            "THE", "AND", "CO", "MAC", "VAN", "A", "SA DE CV MI", "COMPAÑÍA",
            "SRL MI", "DE", "LA", "LAS", "MC", "VON", "DEL", "LOS", "Y", "MAC",
            "VAN", "MI"
    };

    private final Faker faker;
    private final String name;
    private final String nameFather;
    private final String nameMother;
    private final String birthDay;
    private final String sex;
    private final String state;

    /**
     * set the default states of CURP
     *
     * @return a fixed format numeric string
     */
    private void setDefault() {
        codigoverificacion = new HashMap<String, String>();

        codigoverificacion.put("0", "00");
        codigoverificacion.put("1", "01");
        codigoverificacion.put("2", "02");
        codigoverificacion.put("3", "03");
        codigoverificacion.put("4", "04");
        codigoverificacion.put("5", "05");
        codigoverificacion.put("6", "06");
        codigoverificacion.put("7", "07");
        codigoverificacion.put("8", "08");
        codigoverificacion.put("9", "09");
        codigoverificacion.put("A", "10");
        codigoverificacion.put("B", "11");
        codigoverificacion.put("C", "12");
        codigoverificacion.put("D", "13");
        codigoverificacion.put("E", "14");
        codigoverificacion.put("F", "15");
        codigoverificacion.put("G", "16");
        codigoverificacion.put("H", "17");
        codigoverificacion.put("I", "18");
        codigoverificacion.put("J", "19");
        codigoverificacion.put("K", "20");
        codigoverificacion.put("L", "21");
        codigoverificacion.put("M", "22");
        codigoverificacion.put("N", "23");
        codigoverificacion.put("&", "23");
        codigoverificacion.put("O", "25");
        codigoverificacion.put("P", "26");
        codigoverificacion.put("Q", "27");
        codigoverificacion.put("R", "28");
        codigoverificacion.put("S", "29");
        codigoverificacion.put("T", "30");
        codigoverificacion.put("U", "31");
        codigoverificacion.put("V", "32");
        codigoverificacion.put("W", "33");
        codigoverificacion.put("X", "34");
        codigoverificacion.put("Y", "35");
        codigoverificacion.put("Z", "36");
        codigoverificacion.put(" ", "37");
        codigoverificacion.put("Ñ", "38");
    }

    /**
     * constructor of EsMXIdNumber.
     */
    public EsMXIdNumber() {
        this.faker = new Faker(new Locale("es-MX"));
        this.name = faker.name().firstName();
        this.nameFather = faker.name().firstName();
        this.nameMother = faker.name().lastName();
        this.sex = new String[]{"H", "M"}[faker.random().nextInt(2)];
        this.birthDay = getBirthday(faker);
        this.state = new String[]{"AG", "BC", "BS", "CM", "CS",
                "CH", "CO", "CL", "DF", "DG", "GT", "GR",
                "HG", "JA", "EM", "MI", "MO", "NA", "NL",
                "OA", "PU", "QT", "QR", "SL", "SI", "SO",
                "TB", "TM", "TL", "VE", "YU", "ZA", "NE",
        }[faker.random().nextInt(33)];
        setDefault();
    }

    /**
     * Gets the first Vowel Letter.
     *
     * @param cad A string of name.
     * @return the first Vowel Letter.
     */
    private String getFirstVowel(String cad) {

        cad = cad.toUpperCase();
        String vocales = "AEIOU";
        for (int i = 0; i < cad.length(); i++) {
            String c = cad.substring(i, i + 1);
            for (int j = 0; j < vocales.length(); j++) {
                String vc = vocales.substring(j, j + 1);
                if (c.equals(vc)) return vc;
            }

        }
        return "";
    }

    /**
     * Gets the first Consonant Letter.
     *
     * @param cad A string of name.
     * @return the first Consonant Letter.
     */
    private String getFirstConsonant(String cad) {
        cad = cad.toUpperCase();
        String vocales = "AEIOU";
        for (int i = 0; i < cad.length(); i++) {
            String c = cad.substring(i, i + 1);
            if (!vocales.contains(c)) return c;
        }
        return "";
    }

    /**
     * Delete words.
     *
     * @param cad A string of name.
     * @return the string after delete words.
     */
    private String deleteWords(String cad) {
        String nomaux = cad;
        for (String v : nomaux.split(" ")) {
            for (String con : nexosnovalidos) {
                if (v.equals(con))
                    nomaux = nomaux.replace(con, "");
            }
        }
        return nomaux.toUpperCase();
    }

    /**
     * gets The First of the CURP.
     *
     * @return The First of the CURP.
     */
    private String first4Letter() {
        String nameFather_ = deleteWords(nameFather.toUpperCase());
        String nameMother_ = deleteWords(nameMother.toUpperCase());
        String name_ = deleteWords(name.toUpperCase()).replace(" ", "");
        String cad = nameFather_.substring(0, 1) + getFirstVowel(nameMother_) + nameMother_.substring(0, 1) + name_.substring(0, 1);
        for (String mal : palabrasnovalidas) {
            if (cad.equals(mal))
                cad = cad.substring(0, 3) + "X";
        }
        return cad;
    }

    /**
     * Randomly gets a birthday.
     *
     * @param f A specific instance of Faker.
     * @return A valid date.
     */
    private String getBirthday(Faker f) {
        int year = f.random().nextInt(1900, 2021);
        int month = f.random().nextInt(1, 12);
        int day = validDay(year, month, f);
        return String.valueOf(year * 10000 + month * 100 + day);
    }


    /**
     * Gets a valid day according to year and month.
     *
     * @param year  A specific year.
     * @param month A specific month.
     * @param f     A specific instance of Faker.
     * @return A valid day.
     */
    private int validDay(int year, int month, Faker f) {

        List<Integer> bigMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);

        if (month == 2) {
            if (year % 4 == 0) {
                return f.random().nextInt(1, 29);
            } else return f.random().nextInt(1, 28);
        } else if (bigMonths.contains(month)) {
            return f.random().nextInt(1, 31);
        } else return f.random().nextInt(1, 30);

    }

    /**
     * Gets the middle 3 Consonant letters.
     *
     * @return the middle 3 Consonant letters.
     */
    private String middle3Consonant() {
        String aP_ = deleteWords(nameFather.toUpperCase());
        String aM_ = deleteWords(nameMother.toUpperCase());
        String nombre_ = deleteWords(name.toUpperCase()).replace(" ", "");
        return getFirstConsonant(aP_.substring(1)) + getFirstConsonant(aM_.substring(1)) + getFirstConsonant(nombre_.substring(1));
    }

    /**
     * Gets the other letters.
     *
     * @return the other letters.
     */
    private String otherLetter(String rfc) {
        String rfc_ = rfc;
        int suma = 0;
        int v = 18;
        for (int i = 0; i < rfc_.length(); i++) {
            suma += Integer.parseInt(codigoverificacion.get(rfc_.substring(i, i + 1))) * v--;
        }
        int residuo = suma % 10;
        residuo -= 10;
        residuo = (residuo == 10) ? 0 : Math.abs(residuo);
        String penultimo = (Integer.parseInt(birthDay.substring(0, 4)) <= 1999) ? "0" : "A";
        String CURP = rfc + penultimo + residuo;
        for (int i = 0; i < CURP.length(); i++) {
            if (CURP.charAt(i) == 'Ñ') {
                CURP = CURP.substring(0, i) + "N" + CURP.substring(i + CURP.length());
            }
            if (CURP.charAt(i) == ' ') {
                CURP = CURP.substring(0, i) + "N" + CURP.substring(i + CURP.length());
            }
        }
        return CURP;
    }

    /**
     * Get A valid MEX CURP.
     *
     * @return A valid MEX CURP.
     */
    //CS304 Issue link: https://github.com/DiUS/java-faker/issues/571
    public String get() {
        return otherLetter(first4Letter() + birthDay.substring(2, 8) + sex + state + middle3Consonant());
    }

    /**
     * Get A invalid MEX CURP.
     *
     * @return A invalid MEX CURP.
     */
    //CS304 Issue link: https://github.com/DiUS/java-faker/issues/571
    public String getWrong() {
        String[] cha = {"HEFA560427MVZRRL04",
                "DKEM193827HDQWEF05",
                "KAKS142444HNSSFAW6",
                "KSDF414424HNSDFAW6",
                "AKDF414424MSDSFAW6",
                "ADKF144424MNSDFCD6",
                "MYDF144424MDNFAW37",
                "AKKS414424MDAFDFW6",
                "WKDF144244HSDCNFA2",
                "AKSK414244HSDATT56",
                "QWDF414424HNSDVAW4",
                "AKDF144424MDEFVFA1"
        };
        return cha[faker.random().nextInt(12)];
    }

}