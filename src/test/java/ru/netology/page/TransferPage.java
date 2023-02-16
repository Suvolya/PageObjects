package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class TransferPage {
    private SelenideElement amountInput = $("div[data-test-id=amount] input");
    private SelenideElement fromInput = $("span[data-test-id=from] input");
    private SelenideElement transferButton = $("button[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification] .notification__content");

    public TransferPage() {
        transferButton.shouldBe(visible);
    }

    public DashboardPage makevalidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getNumber());
        transferButton.click();
    }

    public void findErrorNotification(String expectedText) {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }
}
