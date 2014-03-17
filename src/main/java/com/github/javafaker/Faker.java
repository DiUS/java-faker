package com.github.javafaker;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sun.deploy.uitoolkit.impl.fx.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.ho.yaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides utility methods for generating fake strings, such as names, phone
 * numbers, addresses. generate random strings with given patterns
 *
 * @author ren
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Faker {
    private static final Logger logger = LoggerFactory.getLogger(Faker.class);
    private static final char[] METHOD_NAME_DELIMITERS = { '_' };

    private Map<String, Object> fakeValuesMap;

    public Faker() {
        this(Locale.ENGLISH);
    }

    public Faker(Locale locale) {
        logger.info("Using default locale " + locale);
        String languageCode = locale.getLanguage();
        Map valuesMap = (Map) Yaml.load(findStream(languageCode + ".yml"));
        valuesMap = (Map) valuesMap.get(languageCode);
        fakeValuesMap = (Map<String, Object>) valuesMap.get("faker");
    }

    private InputStream findStream(String filename) {
      InputStream streamOnClass = getClass().getResourceAsStream(filename);
      if (streamOnClass != null) {
          return streamOnClass;
      }
      return getClass().getClassLoader().getResourceAsStream(filename);
    }

    private List<String> getRandomTextLines(int numberOfLines, int approxLineLength){
        List<String> lines = new ArrayList<String>();
        for (int i = 0; i < numberOfLines; i++){
            lines.add(getRandomTextLine(approxLineLength));
        }
        return lines;
    }

    private String getRandomTextLine(int approxLength){  // approxLength = approximate number of characters
        StringBuilder builder = new StringBuilder();
        int currentLineLength = 0;
        while (currentLineLength < approxLength){
            String word = fetchString("lorem.words");
            builder.append(word);
            currentLineLength += word.length() + 1;
            if (currentLineLength < approxLength){
                builder.append(' ');
            }
        }
        return builder.toString();
    }

    private void writeLinesToFile(String filePath, List<String> lines, boolean append){
        FileWriter textFileWriter = null;
        PrintWriter pw = null;
        try {
            File file = new File(filePath);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            textFileWriter = new FileWriter(filePath, append);
            pw = new PrintWriter(textFileWriter);

            for (String line:lines){
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (textFileWriter != null){
                    textFileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void file(String filePath, int linesNumber) {
        List<String> lines = getRandomTextLines(linesNumber, 80);
        writeLinesToFile(filePath, lines, false);
    }

    public void file(String filePath) {
        List<String> lines = getRandomTextLines(10000, 80);
        writeLinesToFile(filePath, lines, false);
    }

    public String numerify(String numberString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) == '#') {
                sb.append(RandomUtils.nextInt(10));
            } else {
                sb.append(numberString.charAt(i));
            }
        }

        return sb.toString();
    }

    public String letterify(String letterString) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < letterString.length(); i++) {
            if (letterString.charAt(i) == '?') {
                sb.append((char) (97 + RandomUtils.nextInt(26))); // a-z
            } else {
                sb.append(letterString.charAt(i));
            }
        }

        return sb.toString();
    }

    public String bothify(String string) {
        return letterify(numerify(string));
    }

    /**
     * Fetch a random value from an array item specified by the key
     *
     * @param key
     * @return
     */
    public Object fetch(String key) {
        List valuesArray = (List) fetchObject(key);
        return valuesArray.get(RandomUtils.nextInt(valuesArray.size()));
    }

    public String fetchString(String key) {
        return (String) fetch(key);
    }

    /**
     * Return the object selected by the key from yaml file.
     *
     * @param key
     *            key contains path to an object. Path segment is separated by
     *            dot. E.g. name.first_name
     * @return
     */
    public Object fetchObject(String key) {
        String[] path = key.split("\\.");
        Object currentValue = fakeValuesMap;
        for (String pathSection : path) {
            currentValue = ((Map<String, Object>) currentValue).get(pathSection);
        }
        return currentValue;
    }

    public String name() {
        List<String> nameFormat = (List<String>) fetch("name.formats");

        String[] nameParts = new String[nameFormat.size()];
        for (int i = 0; i < nameParts.length; i++) {
            // remove leading colon
            String methodName = nameFormat.get(i).substring(1);
            // convert to camel case
            methodName = WordUtils.capitalizeFully(methodName, METHOD_NAME_DELIMITERS).replaceAll("_", "");
            methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

            try {
                nameParts[i] = (String) Faker.class.getDeclaredMethod(methodName, (Class[]) null).invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        String name = StringUtils.join(nameParts, " ");
        return name;
    }

    public String firstName() {
        return fetchString("name.first_name");
    }

    public String lastName() {
        return fetchString("name.last_name");
    }

    public String prefix() {
        return fetchString("name.prefix");
    }

    public String suffix() {
        return fetchString("name.suffix");
    }

    public String phoneNumber() {
        return numerify(fetchString("phone_number.formats"));
    }

    public String eMail() {
        return bothify(firstName()+"####"+lastName()+"@"+fetchString("internet.free_email"));
    }

    // lorem
    public List<String> words(int num) {
        List<String> words = (List<String>) fetchObject("lorem.words");
        List<String> returnList = new ArrayList();
        for (int i = 0; i < num; i++) {
            returnList.add(words.get(nextInt(words.size())));
        }
        return returnList;
    }

    public int number(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max-min) + min;
    }

    public int number() {
        return number(0, 1000);
    }

    public String date(){
        return date(0, "yyyy-MM-dd HH:mm:ss");
    }

    public String date(int days){
      return date(days, "yyyy-MM-dd HH:mm:ss");
    }

    public String date(int days, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateResult = "";
        if (days == 0){
            Date myDate = new Date();
            dateResult = dateFormat.format(myDate);
        } else {
            Date myDate = new Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(myDate);
            cal.add(Calendar.DAY_OF_MONTH,days);
            dateResult = dateFormat.format(cal.getTime());
        }
        return dateResult;
    }


    public List<String> words() {
        return words(3);
    }

    public String sentence(int wordCount) {
        return capitalize(join(words(wordCount + RandomUtils.nextInt(6)), " ") + ".");
    }

    public String sentence() {
        return sentence(3);
    }

    public List<String> sentences(int sentenceCount) {
        List<String> sentences = new ArrayList<String>(sentenceCount);
        for (int i = 0; i < sentenceCount; i++) {
            sentences.add(sentence());
        }
        return sentences;
    }

    public String paragraph(int sentenceCount) {
        return join(sentences(sentenceCount + nextInt(3)), " ");
    }

    public String paragraph() {
        return paragraph(3);
    }

    public List<String> paragraphs(int paragraphCount) {
        List<String> paragraphs = new ArrayList<String>(paragraphCount);
        for (int i = 0; i < paragraphCount; i++) {
            paragraphs.add(paragraph());
        }
        return paragraphs;
    }

    // address

    public String streetName() {
        List<String> possibleStreetNames = new ArrayList<String>();
        possibleStreetNames.add(join(new Object[] { lastName(), streetSuffix() }, " "));
        possibleStreetNames.add(join(new Object[] { firstName(), streetSuffix() }, " "));
        return possibleStreetNames.get(nextInt(possibleStreetNames.size()));
    }

    public String streetAddress(boolean includeSecondary) {
        String streetAddress = fetchString("address.street_address") + " " + streetName();
        if (includeSecondary) {
            streetAddress = streetAddress + " " + secondaryAddress();
        }
        return numerify(streetAddress);
    }

    public String secondaryAddress() {
        return numerify(fetchString("address.secondary_address"));
    }

    public String zipCode() {
        return bothify(fetchString("address.postcode"));
    }

    public String streetSuffix() {
        return fetchString("address.street_suffix");
    }

    public String citySuffix() {
        return fetchString("address.city_suffix");
    }

    public String cityPrefix() {
        return fetchString("address.city_prefix");
    }

    public String stateAbbr() {
        return fetchString("address.state_abbr");
    }

    public String country() {
        return fetchString("address.country");
    }
}
