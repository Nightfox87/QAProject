package ru.netology.tour.data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {

    }
    @Value
    public static class CardInfo {
        String cardNumber;
        int month;
        String year;
        String name;
        int cvcCode;
    }
    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }
    public static int generateDigits(String digit) {
        Faker faker = new Faker();
        return Integer.parseInt(faker.numerify(digit));
    }

    public static CardInfo getCorrectCardInfo(String locale, int months, int years) {
        return new CardInfo("1111 2222 3333 4444", getMonth(months), getYear(years), generateName(locale), generateDigits("###"));
    }
    public static CardInfo getIncorrectCardInfo(String locale, int months, int years) {
        return new CardInfo("5555 6666 7777 8888", getMonth(months), getYear(years), generateName(locale), generateDigits("###"));
    }
    public static String getYear(int years) {
        LocalDate date = LocalDate.now().plusYears(years);
        int year = date.getYear();
        return Integer.toString(year).substring(2);
    }
    public static int getMonth(int months) {
        LocalDate date = LocalDate.now().plusMonths(months);
        return date.getMonthValue();
    }
    public static String generateCardNumber(CreditCardType type) {
        Faker faker = new Faker();
        return faker.finance().creditCard(type);
    }
    public static String zeroDigitInCVC() {
        return "000";
    }
    public static int nonexistentMonth() {
        int min = 13;
        int max = 99;
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static String generateFirstName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }
    public static String oneLetterName() {
        return "N";
    }
    public static String symbolsInName() {
        return "@#$%^&";
    }
    @SneakyThrows
    public static void getInfoFromDatabase() {
        var statusSQL = "SELECT * FROM payment_entity;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "user", "pass"
                );
        ) {
            var info =  runner.query(conn, statusSQL, new BeanListHandler<>(Database.class));
        }
    }
    @Value
    public static class Database {
        String id;
        int amount;
        String created;
        String status;
        String transaction_id;
    }

}
