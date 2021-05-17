package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;

//CS304 Issue link: https://github.com/DiUS/java-faker/issues/588
public class ZhCNIdNumber {

    /**
     * Gets a valid Chinese ID number.
     * @param f A specific instance of Faker.
     * @return A fake but valid Chinese ID number.
     */
    public String getValidCN(Faker f) {
        String idNumber = "";
        String addressNumber = getAddress(f);           //1~6
        String birthdayNumber = getBirthday(f);         //7~14
        String sequenceNumber = getSequenceNumber(f);   //15~17

        idNumber = addressNumber + birthdayNumber + sequenceNumber;

        String checkSum = calCheckSumOfId(f, idNumber);

        idNumber += checkSum;   //18

        return idNumber;
    }

    /**
     * Gets an invalid Chinese ID number.
     * @param f A specific instance of Faker.
     * @return A fake and invalid Chinese ID number.
     */
    public String getInvalidCN(Faker f) {
        String invalidIdNumber = f.numerify("##################");
        while (!invalidCheck(f, invalidIdNumber)){
            invalidIdNumber = f.numerify("##################");
        }

        return invalidIdNumber;
    }

    /**
     * Checks if the ID number is invalid.
     * @param f A specific instance of Faker.
     * @param invalidIdNumber An ID number which may be invalid.
     * @return A boolean data representing if the ID is invalid or not.
     */
    boolean invalidCheck(Faker f, String invalidIdNumber) {
        int n = invalidIdNumber.length();
        return !calCheckSumOfId(f,
                invalidIdNumber.substring(0, n - 1)).equals(invalidIdNumber.substring(n - 1));
    }

    /**
     * Different province numbers in China.
     */
    public List<String> provinces = Arrays.asList(
            "11","12","13","14","15",
            "21","22","23",
            "31","32","33","34","35","36","37",
            "41","42","43","44","45","46",
            "50","51","52","53","54",
            "61","62","63","64","65",
            "81","82"
    );

    /**
     * Makes an address number, first part of Chinese ID number.
     * @param f A specific instance of Faker.
     * @return An address number made of province, city and district numbers.
     */
    private String getAddress(Faker f){
        String provinceNumber = getProvince(f);
        String cityNumber = getCity(f);
        String districtNumber = getDistrict(f);
        String addressNumber = provinceNumber + cityNumber + districtNumber;
        return addressNumber;

    }

    /**
     * Randomly gets a province number in range.
     * @param f A specific instance of Faker.
     * @return A province number.
     */
    private String getProvince(Faker f){
        int index = f.random().nextInt(provinces.size());
        String provinceNumber = provinces.get(index);
//        String provinceNumber = f.resolve("id_number.provinceCode");
        return provinceNumber;
    }

    /**
     * Randomly gets a city number.
     * @param f A specific instance of Faker.
     * @return A city number.
     */
    private String getCity(Faker f){
        String cityNumber = f.numerify("##");
        return cityNumber;
    }

    /**
     * Randomly gets a district number.
     * @param f A specific instance of Faker.
     * @return A district number.
     */
    private String getDistrict(Faker f){
        String cityNumber = f.numerify("##");
        return cityNumber;
    }

    /**
     * Randomly gets a birthday.
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
     * @param year A specific year.
     * @param month A specific month.
     * @param f A specific instance of Faker.
     * @return A valid day.
     */
    private int validDay(int year, int month, Faker f) {

        List<Integer> bigMonths = Arrays.asList(1,3,5,7,8,10,12);
//        List<Integer> smallMonths = Arrays.asList(4,6,9,11);

        if (month == 2) {
            if (year % 4 == 0) {
                return f.random().nextInt(1, 29);
            }
            else {
                return f.random().nextInt(1, 28);
            }
        }

        else if (bigMonths.contains(month)) {
            return f.random().nextInt(1, 31);
        }
        else {
            return f.random().nextInt(1, 30);
        }

    }

    /**
     * Randomly gets a sequence number.
     * @param f A specific instance of Faker.
     * @return A sequence number.
     */
    private String getSequenceNumber(Faker f) {
        String seqNumber = f.numerify("###");
        return seqNumber;
    }


    /**
     * Calculate check sum of a specific Chinese ID number.
     * @param f A specific instance of Faker.
     * @param idNumber A string of id number to be checked.
     * @return The checksum of this Chinese ID number.
     */
    private String calCheckSumOfId(Faker f, String idNumber) {
        int[] weights = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        int checksum = 0;
        int sum = 0;
        int Y;
        try {
            for (int i = 0;i<idNumber.length();i++){
                sum += weights[i] * Integer.parseInt(idNumber.substring(i, i+1));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        Y = sum % 11;
        checksum = (12 - Y) % 11;
        if(checksum == 10) {
            return "X";
        }
        return String.valueOf(checksum);
    }

}
