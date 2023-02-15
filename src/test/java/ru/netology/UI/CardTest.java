package ru.netology.UI;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

        @Test
        void shouldSubmitRequest() {
            open("http://localhost:7777");
            SelenideElement form = $("form");//$("[form class=form_theme_alfa-on-white]");
            form.$("[data-test-id=name] input").setValue("Василий");
            form.$("[data-test-id=phone] input").setValue("+79270000000");
            form.$("[data-test-id=agreement]").click();
            form.$("[type=button]").click();
            $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        }

}
