package ru.netology.tour.page;

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
    private SelenideElement nameField = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement cvcCodeField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement successNotification = $("[class*=notification_status_ok]");
    private SelenideElement errorNotification = $("[class*=notification_status_error]");
    private SelenideElement incorrectMonthNote = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expiredYearNote = $(byText("Истёк срок действия карты"));
    private SelenideElement wrongFormatCardNumber = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]"));
    private SelenideElement wrongFormatMonth = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]"));
    private SelenideElement wrongFormatYear = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]"));
    private SelenideElement wrongFormatCvcCode = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]"));
    private SelenideElement emptyNameField = $(byText("Поле обязательно для заполнения"));



    public CardPage payForTheTour(DataGenerator.CardInfo info) {
        buyButton.click();
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(String.valueOf(info.getMonth()));
        yearField.setValue(String.valueOf(info.getYear()));
        nameField.setValue(info.getName());
        cvcCodeField.setValue(String.valueOf(info.getCvcCode()));
        return new CardPage();
    }
    public void shouldAppearSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
    public void shouldAppearErrorNotification() { errorNotification.shouldBe(visible, Duration.ofSeconds(15)); }
    public void shouldClickContinueButton() {
        continueButton.click();
    }
    public void clearMonthField() {
        monthField.doubleClick();
        monthField.sendKeys(Keys.BACK_SPACE);
    }
    public void shouldSetMonth(int month) {
        monthField.setValue(String.valueOf(month));
    }
    public void incorrectMonthNotification(){
        incorrectMonthNote.shouldBe(visible);
    }
    public void clearYearField() {
        yearField.doubleClick();
        yearField.sendKeys(Keys.BACK_SPACE);
    }
    public void shouldSetYear(int year) {
        yearField.setValue(String.valueOf(year));
    }
    public void clearCvcField() {
        cvcCodeField.doubleClick();
        cvcCodeField.sendKeys(Keys.BACK_SPACE);
    }
    public void shouldSetCVC(String code) {cvcCodeField.setValue(code);}
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
    public void expiredYearNotification() {
        expiredYearNote.shouldBe(visible);
    }
    public void shouldClickBuyButton() {buyButton.click();}
    public void wrongFormatNotificationCardNumber() {wrongFormatCardNumber.shouldBe(visible);}
    public void wrongFormatNotificationMonth() {wrongFormatMonth.shouldBe(visible);}
    public void wrongFormatNotificationYear() {wrongFormatYear.shouldBe(visible);}
    public void wrongFormatNotificationCvc() { wrongFormatCvcCode.shouldBe(visible);}
    public void emptyNameFieldNotification() {emptyNameField.shouldBe(visible);}

}
