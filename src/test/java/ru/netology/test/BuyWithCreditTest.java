package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataBaseHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyWithCreditTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void shouldCleanDataBaseAndOpenWeb() {
        DataBaseHelper.cleanDataBase();
        open("http://localhost:8080", MainPage.class);
        mainPage.buyWithCredit();

    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldApproveFirstCard() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.expectApprovalFromBank();
        val expected = DataHelper.getFirstCardExpectedStatus();
        val actual = DataBaseHelper.getStatusPaymentWithCredit();
        assertEquals(expected, actual);
    }

    @Test
    void shouldRejectSecondCard() {
        val cardNumber = DataHelper.getSecondCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.expectRejectionFromBank();
        val expected = DataHelper.getSecondCardExpectedStatus();
        val actual = DataBaseHelper.getStatusPaymentWithCredit();
        assertEquals(expected, actual);
    }

    @Test
    void shouldRejectDifferentCard() {
        val cardNumber = DataHelper.getCardNumberDifferent();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.expectRejectionFromBank();
    }

    @Test
    void shouldRejectEmptyCardNumber() {
        val cardNumber = DataHelper.getCardNumberEmpty();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectEmptyMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectInvalidMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidDuration();
    }

    @Test
    void shouldRejectZeroMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getZeroMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidDuration();
    }

    @Test
    void shouldRejectEmptyYear() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectInvalidYear() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getInvalidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidYear();
    }

    @Test
    void shouldRejectEmptyOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getEmptyOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitNecessaryFillOutField();
    }

    @Test
    void shouldRejectInvalidOwner() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectEmptyCvс() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getEmptyCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectInvalidCvс() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getInvalidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectYearMoreThanFive() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getInvalidYearMoreThanFive();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidDuration();
    }

    @Test
    void shouldRejectOwnerRus() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getValidMonth();
        val year = DataHelper.getValidYear();
        val owner = DataHelper.getInvalidOwnerRus();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidFormat();
    }

    @Test
    void shouldRejectPastMonth() {
        val cardNumber = DataHelper.getFirstCardNumber();
        val month = DataHelper.getPastMonth();
        val year = DataHelper.getThisYear();
        val owner = DataHelper.getValidOwner();
        val cvс = DataHelper.getValidCvс();
        paymentPage.fillOutFields(cardNumber, month, year, owner, cvс);
        paymentPage.waitInvalidDuration();
    }
}

