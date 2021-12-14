package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String getFirstCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getFirstCardExpectedStatus() {
        return "APPROVED";
    }

    public static String getSecondCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getSecondCardExpectedStatus() {
        return "DECLINED";
    }

    public static String getCardNumberDifferent() {
        return faker.business().creditCardNumber();
    }

    public static String getCardNumberEmpty() {
        return "";
    }

    public static String getValidMonth() {
        return "07";
    }

    public static String getInvalidMonth() {
        return "14";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getValidYear() {
        return "23";
    }

    public static String getInvalidYear() {
        return "20";
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getValidOwner() {
        return faker.name().firstName() + " " + faker.name().lastName().replaceAll("[^A-Za-z]", "");
    }

    public static String getInvalidOwner() {
        return "Jhon#*?%731";
    }

    public static String getEmptyOwner() {
        return "";
    }

    public static String getValidCvс() {
        return "123";
    }

    public static String getInvalidCvс() {
        return "12";
    }

    public static String getEmptyCvс() {
        return "";
    }

    public static String getInvalidYearMoreThanFive() {
        return "27";
    }

    public static String getInvalidOwnerRus() {
        return "Василий Петров";
    }

    public static String getPastMonth() {
        return "11";
    }

    public static String getThisYear() {
        return "21";
    }
}
