package ru.netology.tour.data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {

    }
    @Value
    public static class CardInfo {
        String cardNumber;
        int month;
        int year;
        String name;
        int cvcCode;
    }
    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static CardInfo getCorrectCardInfo(String locale) {
        return new CardInfo("1111 2222 3333 4444", 11, 22, generateName(locale), 333);
    }
    public static CardInfo getIncorrectCardInfo(String locale) {
        return new CardInfo("5555 6666 7777 8888", 11, 22, generateName(locale), 333);
    }
}
