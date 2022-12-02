package ru.netology.tour.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.tour.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CardPage {
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement nameField = $(byXpath("//*[contains(text(), \"Владелец\")]/..//input"));
    private SelenideElement cvcCodeField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $("[class*=notification_status_ok]");
    private SelenideElement errorNotification = $("[class*=notification_status_error]");
    private SelenideElement incorrectMonthNotification = $(byXpath("//*[@placeholder=\"08\"]/..//../*[@class=\"input__sub\"]"));
    private SelenideElement incorrectYearNotification = $(byXpath("//*[@placeholder=\"22\"]/..//../*[@class=\"input__sub\"]"));
    private SelenideElement incorrectCardNumberNotification = $(byXpath("//*[@placeholder=\"0000 0000 0000 0000\"]/..//../*[@class=\"input__sub\"]"));
    private SelenideElement incorrectCvcCodeNotification = $(byXpath("//*[@placeholder=\"999\"]/..//../*[@class=\"input__sub\"]"));
    private SelenideElement incorrectNameNotification = $(byXpath("//*[contains(text(), \"Владелец\")]/..//*[@class=\"input__sub\"]"));


    public void payForTheTour(DataGenerator.CardInfo info) {
        buyButton.click();
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(String.valueOf(info.getMonth()));
        yearField.setValue(String.valueOf(info.getYear()));
        nameField.setValue(info.getName());
        cvcCodeField.setValue(String.valueOf(info.getCvcCode()));
    }

    public void shouldAppearSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldAppearErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldClickContinueButton() {
        continueButton.click();
    }

    public void clearMonthField() {
        monthField.doubleClick();
        monthField.sendKeys(Keys.BACK_SPACE);
    }

    public void shouldSetMonth(String month) {
        monthField.setValue(String.valueOf(month));
    }


    public void clearYearField() {
        yearField.doubleClick();
        yearField.sendKeys(Keys.BACK_SPACE);
    }

    public void shouldSetYear(String year) {
        yearField.setValue(String.valueOf(year));
    }

    public void clearCvcField() {
        cvcCodeField.doubleClick();
        cvcCodeField.sendKeys(Keys.BACK_SPACE);
    }

    public void shouldSetCVC(String code) {
        cvcCodeField.setValue(code);
    }

    public void clearCardNumberField() {
        cardNumberField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        cardNumberField.sendKeys(Keys.BACK_SPACE);
    }

    public void shouldSetCardNumber(String cardNumber) {
        cardNumberField.setValue(cardNumber);
    }

    public void clearNameField() {
        nameField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        nameField.sendKeys(Keys.BACK_SPACE);
    }

    public void shouldSetNameField(String name) {
        nameField.setValue(name);
    }

    public void shouldClickBuyButton() {
        buyButton.click();
    }

    public void shouldShowIncorrectMonthNotification(String notificationText) {

        incorrectMonthNotification
                .shouldHave(Condition.text(notificationText))
                .shouldBe(visible);
    }

    public void shouldShowIncorrectYearNotification(String notificationText) {
        incorrectYearNotification
                .shouldHave(Condition.text(notificationText))
                .shouldBe(visible);
    }

    public void shouldShowIncorrectCardNumberNotification(String notificationText) {
        incorrectCardNumberNotification
                .shouldHave(Condition.text(notificationText))
                .shouldBe(visible);
    }

    public void shouldShowIncorrectCVCNotification(String notificationText) {
        incorrectCvcCodeNotification
                .shouldHave(Condition.text(notificationText))
                .shouldBe(visible);
    }

    public void shouldShowIncorrectNameNotification(String notificationText) {
        incorrectNameNotification
                .shouldHave(Condition.text(notificationText))
                .shouldBe(visible);
    }

}
