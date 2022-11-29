package ru.netology.tour.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.CreditCardType;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.tour.data.DataGenerator;
import ru.netology.tour.page.CardPage;

import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldBuyTour() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearSuccessNotification();
    }


    @Test
    void shouldNotBuyTourWithDeclinedCard() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getIncorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }

    @Test
    void shouldNotSendEmptyForm() {
        var cardPage = new CardPage();
        cardPage.shouldClickBuyButton();
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCardNumber();
        cardPage.wrongFormatNotificationMonth();
        cardPage.wrongFormatNotificationYear();
        cardPage.emptyNameFieldNotification();
        cardPage.wrongFormatNotificationCvc();
    }
    @Test
    void shouldNotSendFormWithIncorrectMonth() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(DataGenerator.nonexistentMonth());
        cardPage.shouldClickContinueButton();
        cardPage.incorrectMonthNotification();
    }
    @Test
    void shouldNotSendFormWithExpiredYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 0, -1);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.expiredYearNotification();
    }
    @Test
    void shouldNotSendFormWithOneDigitMonth() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(DataGenerator.generateDigits("#"));
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationMonth();
    }
    @Test
    void shouldNotSendFormWithOneDigitYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearYearField();
        cardPage.shouldSetYear(DataGenerator.generateDigits("#"));
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationYear();
    }
    @Test
    void shouldNotSendFormWithSixPlusYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 0, 8);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationYear();
    }
    @Test
    void shouldNotSendFormWith3ZeroCVC() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCvcField();
        cardPage.shouldSetCVC(DataGenerator.zeroDigitInCVC());
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCvc();
    }
    @Test
    void shouldNotSendFormWithOneDigitCVC() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCvcField();
        cardPage.shouldSetCVC(String.valueOf(DataGenerator.generateDigits("#")));
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCvc();
    }
    @Test
    void shouldNotSendFormWithOneDigitCardNumber() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCardNumberField();
        cardPage.shouldSetCardNumber(String.valueOf(DataGenerator.generateDigits("#")));
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCardNumber();
    }
    @Test
    void shouldNotBuyTourWithAnotherCard() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCardNumberField();
        cardPage.shouldSetCardNumber(DataGenerator.generateCardNumber(CreditCardType.valueOf("MASTERCARD")));
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithRussianName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("ru", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithDigitsInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(String.valueOf(DataGenerator.generateDigits("#####")));
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithSymbolsInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.symbolsInName());
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithOneLetterInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.oneLetterName());
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithoutSurname() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 0);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.generateFirstName("en"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }


}
