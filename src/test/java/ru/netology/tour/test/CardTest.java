package ru.netology.tour.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.CreditCardType;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
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
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearSuccessNotification();
        Assertions.assertEquals("APPROVED", DataGenerator.getStatusFromDatabase());
    }

    @Test
    void shouldNotBuyTourWithDeclinedCard() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getIncorrectCardInfo("en", 0, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
        Assertions.assertEquals("DECLINED", DataGenerator.getStatusFromDatabase());
    }

    @Test
    void shouldNotSendEmptyForm() {
        var cardPage = new CardPage();
        cardPage.shouldClickBuyButton();
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectCardNumberNotification("Неверный формат");
        cardPage.shouldShowIncorrectMonthNotification("Неверный формат");
        cardPage.shouldShowIncorrectYearNotification("Неверный формат");
        cardPage.shouldShowIncorrectNameNotification("Поле обязательно для заполнения");
        cardPage.shouldShowIncorrectCVCNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithIncorrectMonth() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(String.valueOf(DataGenerator.nonexistentMonth()));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectMonthNotification("Неверно указан срок действия карты");
    }

    @Test
    void shouldNotSendFormWithExpiredYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 0, -1);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectYearNotification("Истёк срок действия карты");
    }

    @Test
    void shouldNotSendFormWithOneDigitMonth() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(DataGenerator.generateDigits("#"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectMonthNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithOneDigitYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearYearField();
        cardPage.shouldSetYear(DataGenerator.generateDigits("#"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectYearNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithSixPlusYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 0, 8);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectYearNotification("Неверно указан срок действия карты");
    }

    @Test
    void shouldNotSendFormWith3ZeroCVC() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCvcField();
        cardPage.shouldSetCVC(DataGenerator.zeroDigitInCVC());
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectCVCNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithOneDigitCVC() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCvcField();
        cardPage.shouldSetCVC(DataGenerator.generateDigits("#"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectCVCNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithOneDigitCardNumber() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCardNumberField();
        cardPage.shouldSetCardNumber(DataGenerator.generateDigits("#"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectCardNumberNotification("Неверный формат");
    }

    @Test
    void shouldNotBuyTourWithAnotherCard() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCardNumberField();
        cardPage.shouldSetCardNumber(DataGenerator.generateCardNumber(CreditCardType.valueOf("MASTERCARD")));
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }

    @Test
    void shouldNotSendFormWithRussianName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("ru", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectNameNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithDigitsInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.generateDigits("#####"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectNameNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithSymbolsInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.symbolsInName());
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectNameNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithOneLetterInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.oneLetterName());
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectNameNotification("Неверный формат");
    }

    @Test
    void shouldNotSendFormWithoutSurname() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en", 1, 1);
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField(DataGenerator.generateFirstName("en"));
        cardPage.shouldClickContinueButton();
        cardPage.shouldShowIncorrectNameNotification("Неверный формат");
    }


}
