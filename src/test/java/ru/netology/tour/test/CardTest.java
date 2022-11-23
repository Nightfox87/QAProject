package ru.netology.tour.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.tour.data.DataGenerator;
import ru.netology.tour.page.CardPage;

import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
        var cardPage = new CardPage();
    }

    @Test
    void shouldBuyTour() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearSuccessNotification();
    }
    /*@Test
    void shouldGetInfoFromDatabase() {}*/

    @Test
    void shouldNotBuyTourWithDeclinedCard() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getIncorrectCardInfo("en");
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
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(17);
        cardPage.shouldClickContinueButton();
        cardPage.incorrectMonthNotification();
    }
    @Test
    void shouldNotSendFormWithExpiredYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearYearField();
        cardPage.shouldSetYear(21);
        cardPage.shouldClickContinueButton();
        cardPage.expiredYearNotification();
    }
    @Test
    void shouldNotSendFormWithOneDigitMonth() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(9);
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationMonth();
    }
    @Test
    void shouldNotSendFormWithOneDigitYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearYearField();
        cardPage.shouldSetYear(9);
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationYear();
    }
    @Test
    void shouldNotSendFormWithSixPlusYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearYearField();
        cardPage.shouldSetYear(30);
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationYear();
    }
    @Test
    void shouldNotSendFormWith3ZeroCVC() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCvcField();
        cardPage.shouldSetCVC("000");
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCvc();
    }
    @Test
    void shouldNotSendFormWithOneDigitCVC() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCvcField();
        cardPage.shouldSetCVC("1");
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCvc();
    }
    @Test
    void shouldNotSendFormWithOneDigitCardNumber() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCardNumberField();
        cardPage.shouldSetCardNumber("1");
        cardPage.shouldClickContinueButton();
        cardPage.wrongFormatNotificationCardNumber();
    }
    @Test
    void shouldNotBuyTourWithAnotherCard() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearCardNumberField();
        cardPage.shouldSetCardNumber("2222 2222 2222 2222");
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithRussianName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("ru");
        cardPage.payForTheTour(cardInfo);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithDigitsInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField("12345678");
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithSymbolsInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField("@#$%^&");
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }
    @Test
    void shouldNotSendFormWithOneLetterInName() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearNameField();
        cardPage.shouldSetNameField("N");
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearErrorNotification();
    }


}
