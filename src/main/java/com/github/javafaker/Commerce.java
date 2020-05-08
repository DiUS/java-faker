package com.github.javafaker;

import com.github.javafaker.service.FakeValuesInterface;
import com.github.javafaker.service.FakeValuesService;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public class Commerce {
    private final Faker faker;

    protected Commerce(Faker faker) {
        this.faker = faker;
    }

    public String color() {
        return faker.fakeValuesService().resolve("color.name", this, faker);
    }

    public String department() {
        int numberOfDepartments = Math.max(faker.random().nextInt(4), 1);
        SortedSet<String> departments = new TreeSet<String>();
        while (departments.size() < numberOfDepartments) {
            departments.add(faker.fakeValuesService().resolve("commerce.department", this, faker));
        }
        if (departments.size() > 1) {
            String lastDepartment = departments.last();
            return StringUtils.join(departments.headSet(lastDepartment), ", ") + " & " + lastDepartment;
        } else {
            return departments.first();
        }
    }

    public String productName() {
        return StringUtils.join(new String[] {
                faker.fakeValuesService().resolve("commerce.product_name.adjective", this,faker),
                faker.fakeValuesService().resolve("commerce.product_name.material", this, faker),
                faker.fakeValuesService().resolve("commerce.product_name.product", this, faker) }, " ");
    }

    public String material() {
        return faker.fakeValuesService().resolve("commerce.product_name.material", this, faker);
    }

    /**
     * Generate a random price between 0.00 and 100.00
     */
    public String price() throws IllegalAccessException, NoSuchFieldException {
        return price(0, 100);
    }

    /**
     * Generate a random price between min and max, the format depends on locale
     * @param min the smaller number, also the inclusive lower bound of generated number
     * @param max the larger number, also the exclusive upper bound of generated number
     * @return A generated number between min and max, its format depends on locale
     * @throws IllegalAccessException An exception happens when get fields.
     */
    public String price(double min, double max) throws IllegalAccessException, NoSuchFieldException {
        double price =  min + (faker.random().nextDouble() * (max - min));
        Locale locale = new Locale("en");
        Field fakeValuesServiceField=Faker.class.getDeclaredField("fakeValuesService");
        fakeValuesServiceField.setAccessible(true);
        FakeValuesService fakeValuesService=(FakeValuesService) fakeValuesServiceField.get(faker);
        Field fakeValuesListField=fakeValuesService.getClass().getDeclaredField("fakeValuesList");
        fakeValuesListField.setAccessible(true);
        List<FakeValuesInterface> localLists=( List<FakeValuesInterface>) fakeValuesListField.get(fakeValuesService);
        for(FakeValuesInterface currentInterface : localLists){
            if (currentInterface.getClass().getSimpleName().equals("FakeValues")){
                Field localeField = currentInterface.getClass().getDeclaredField("locale");
                localeField.setAccessible(true);
                locale=(Locale) localeField.get(currentInterface);
                localeField.setAccessible(false);
            }
        }
        fakeValuesListField.setAccessible(false);
        fakeValuesServiceField.setAccessible(false);
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("#0.00");
        return decimalFormat.format(price);
    }

    public String promotionCode() {
        return promotionCode(6);
    }

    public String promotionCode(int digits) {
        return StringUtils.join(faker.resolve("commerce.promotion_code.adjective"),
                faker.resolve("commerce.promotion_code.noun"),
                faker.number().digits(digits));
    }
}
