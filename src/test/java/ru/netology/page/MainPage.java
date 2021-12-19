package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement buttonBuy = $(byText("Купить"));
    private SelenideElement buttonBuyByCredit = $(byText("Купить в кредит"));
    private SelenideElement byCard = $(byText("Оплата по карте"));
    private SelenideElement byCredit = $(byText("Кредит по данным карты"));
//    private SelenideElement buttonBuy = $("[class='button button_size_m button_theme_alfa-on-white']");
//    private SelenideElement buttonBuyByCredit = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0);


    public PaymentPage buyWithoutCredit() {
        buttonBuy.click();
        byCard.shouldBe(visible);
        return new PaymentPage();
    }

    public CreditPage buyWithCredit() {
        buttonBuyByCredit.click();
        byCredit.shouldBe(visible);
        return new CreditPage();
    }
}
