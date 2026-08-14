package com.example.apitesting;

import com.example.apitesting.config.RestAssuredConfig;
import com.example.apitesting.data.TestDataFactory;
import io.qameta.allure.*;
import com.example.apitesting.models.SendUserRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.apitesting.specs.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@Epic("API Тестирование: Создание заявок")
@Feature("Позитивные сценарии")
public class CreateApplicationTests {

    @BeforeClass
    public void setup() {
        Allure.step("Настройка конфигурации RestAssured");
        RestAssuredConfig.setup();
    }

    @Test(description = "ZAJ-239: Создание заявки на рождение (birth)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Создание заявки на рождение")
    @Description("Тест проверяет успешное создание заявки на рождение с валидными данными")
    public void shouldCreateBirthApplication() {
        Allure.step("1. Отправка запроса");
        SendUserRequest request = TestDataFactory.createBirthRequest();
        Allure.step("Отправка запроса");
        given()
                .spec(Specifications.userSpec())
                .body(request)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(200)
                .body("data.applicationid", greaterThan(0));
    }

    @Test(description = "ZAJ-238: Создание заявки на бракосочетание (wedding)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Создание заявки на рождение")
    @Description("Тест проверяет успешное создание заявки на рождение с валидными данными")
    public void shouldCreateMarriageApplication() {
        Allure.step("1. Отправка запроса");
        SendUserRequest request = TestDataFactory.createMarriageRequest();

        given()
                .spec(Specifications.userSpec())
                .body(request)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(200)
                .body("data.applicationid", greaterThan(0));
    }

    @Test(description = "ZAJ-240: Создание заявки на смерть (death)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Создание заявки на рождение")
    @Description("Тест проверяет успешное создание заявки на рождение с валидными данными")
    public void shouldCreateDeathApplication() {
        Allure.step("1. Отправка запроса");
        SendUserRequest request = TestDataFactory.createDeathRequest();

        given()
                .spec(Specifications.userSpec())
                .body(request)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(200)
                .body("data.applicationid", greaterThan(0));
    }

    @Test(description = "Негативный тест: ошибка при отсутствии обязательного поля")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Отсутствие обязательного поля")
    @Description("Тест проверяет появления статус кода 500, при отсутствии обязательного поля LastName")
    public void shouldFailWhenLastNameMissing() {
        Allure.step("1. Отправка запроса");
        SendUserRequest request = TestDataFactory.createRequestWithoutLastName();
        given()
                .spec(Specifications.userSpec())
                .body(request)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(500);
    }
}