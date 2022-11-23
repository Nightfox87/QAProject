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
    private SelenideElement incorrectMonthNote = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expiredYearNote = $(byText("Истёк срок действия карты"));

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
    public void shouldAppearIncorrectMonthNotification(){
        incorrectMonthNote.shouldBe(visible);
    }
    public void clearYearField() {
        yearField.doubleClick();
        yearField.sendKeys(Keys.BACK_SPACE);
    }
    public void shouldSetYear(int year) {
        yearField.setValue(String.valueOf(year));
    }
    public void shouldAppearExpiredYearNotification() {
        expiredYearNote.shouldBe(visible);
    }

}
