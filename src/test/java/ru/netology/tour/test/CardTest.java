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
    void shouldNotSendEmptyForm() {

    }*/

    @Test
    void shouldNotSendFormWithIncorrectMonth() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearMonthField();
        cardPage.shouldSetMonth(17);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearIncorrectMonthNotification();
    }
    @Test
    void shouldNotSendFormWithExpiredYear() {
        var cardPage = new CardPage();
        var cardInfo = DataGenerator.getCorrectCardInfo("en");
        cardPage.payForTheTour(cardInfo);
        cardPage.clearYearField();
        cardPage.shouldSetYear(21);
        cardPage.shouldClickContinueButton();
        cardPage.shouldAppearExpiredYearNotification();
    }


}
