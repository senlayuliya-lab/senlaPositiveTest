package com.example.apitesting;

import com.example.apitesting.config.RestAssuredConfig;
import com.example.apitesting.data.TestDataFactory;
import io.qameta.allure.*;
import com.example.apitesting.models.ApplicationResponse;
import com.example.apitesting.models.ApplicationStatus;
import com.example.apitesting.models.SendUserRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.apitesting.specs.Specifications;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Тестирование: Получение списка и статуса заявок")
@Feature("Позитивные сценарии")
public class GetStatusTests {

    @BeforeClass
    public void setup() {
        Allure.step("Настройка конфигурации RestAssured");
        RestAssuredConfig.setup();
    }

    @Test(description = "ZAJ-272: Получение статуса заявки")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Получение статуса заявки")
    @Description("Тест создает заявку, затем получает её статус и проверяет, что статус заполнен")
    public void shouldGetApplicationStatus() {
        SendUserRequest request = TestDataFactory.createBirthRequest();
        Allure.step("1. Создание заявки на рождение");
        ApplicationResponse createResponse = given()
                .spec(Specifications.userSpec())
                .body(request)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(200)
                .extract()
                .as(ApplicationResponse.class);


        int applicationId = createResponse.getResponseData().getApplicationid();
        System.out.println("Created application with ID: " + applicationId);

        ApplicationStatus statusResponse = given()
                .spec(Specifications.userSpec())
                .pathParam("applicationId", applicationId)
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(200)
                .extract()
                .as(ApplicationStatus.class);
        Allure.step("2. Проверка статуса");
        assertThat(statusResponse).isNotNull();
        assertThat(statusResponse.getStatusofapplication())
                .as("Статус должен быть указан")
                .isNotNull()
                .isNotBlank();

        System.out.println("Application status: " + statusResponse.getStatusofapplication());
        System.out.println("Application kind: " + statusResponse.getKindofapplication());
    }
    @Test(description = "ZAJ-306: Получение списка всех заявок")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение списка заявок")
    @Description("Тест получает список всех заявок и проверяет, что ответ содержит data и total")
    public void shouldGetApplicationsList() {
        Allure.step("1. Получение списка заявок");
        String response = given()
                .spec(Specifications.userSpec())
                .when()
                .get("/getApplications")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        assertThat(response).contains("data");
        assertThat(response).contains("total");
        System.out.println("Applications list received successfully");
    }
}