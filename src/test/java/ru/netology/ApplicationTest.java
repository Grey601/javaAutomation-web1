package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationTest {

    @Test
    void shouldSendValidData() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Гревцов Сергей");
        $("[data-test-id='phone'] input").sendKeys("+79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $("[data-test-id='order-success']").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendValidNameWithHyphen() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Мамин-Сибиряк Дмитрий");
        $("[data-test-id='phone'] input").sendKeys("+79654792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $("[data-test-id='order-success']").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSendInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Grevtsov Sergei");
        $("[data-test-id='phone'] input").sendKeys("+79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_text .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendInvalidName2() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Грев39 Сергей");
        $("[data-test-id='phone'] input").sendKeys("+79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_text .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendInvalidName3() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("%вцов Сергей");
        $("[data-test-id='phone'] input").sendKeys("+79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_text .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendWithoutName() {
        open("http://localhost:9999");
        $("[data-test-id='phone'] input").sendKeys("+79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_text .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendWithOnlySpaceInName() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys(" ");
        $("[data-test-id='phone'] input").sendKeys("+79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_text .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendWithoutPlusInPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Гревцов Сергей");
        $("[data-test-id='phone'] input").sendKeys("79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_tel .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendWithoutPhoneNumber() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Гревцов Сергей");
       // $("[data-test-id='phone'] input").sendKeys("79164792743");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_tel .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendWithInvalidPhoneNumberLength() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Гревцов Сергей");
        $("[data-test-id='phone'] input").sendKeys("+791647927439");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_tel .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendWithInvalidPhoneNumberLength2() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Гревцов Сергей");
        $("[data-test-id='phone'] input").sendKeys("+7916479274");
        $("[data-test-id='agreement'] span").click();
        $("button").click();

        $(".input_type_tel .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendWithoutCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").sendKeys("Гревцов Сергей");
        $("[data-test-id='phone'] input").sendKeys("+79164792744");
        $(".button").click();

        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}