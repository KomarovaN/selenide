package ru.netology.ui;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    @Test
    public void shouldSubmitRequest() {
        open("http://localhost:7777");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue("Василий");
        form.$("[data-test-id='phone'] input").setValue("+79270000000");
        form.$("[data-test-id='agreement']").click();
        form.$("[type=button]").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldNameNull() {
        open("http://localhost:7777");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue(null);
        form.$("[data-test-id='phone'] input").setValue("+79270000000");
        form.$("[data-test-id='agreement']").click();
        form.$("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldPhoneNull() {
        open("http://localhost:7777");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue("Василий");
        form.$("[data-test-id='phone'] input").setValue(null);
        form.$("[data-test-id='agreement']").click();
        form.$("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNameNegative() {
        open("http://localhost:7777");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue("Vasily");
        form.$("[data-test-id='phone'] input").setValue("+79270000000");
        form.$("[data-test-id='agreement']").click();
        form.$("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldPhoneNegative() {
        open("http://localhost:7777");
        SelenideElement form = $("form");
        form.$("[data-test-id='name'] input").setValue("Василий");
        form.$("[data-test-id='phone'] input").setValue("9270000000");
        form.$("[data-test-id='agreement']").click();
        form.$("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldAgreementNoClick() {
        open("http://localhost:7777");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[type=button]").click();
        $("[data-test-id=agreement].input_invalid").should(exist);
    }

}
