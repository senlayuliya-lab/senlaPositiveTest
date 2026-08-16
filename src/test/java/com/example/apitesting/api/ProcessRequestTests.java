package com.example.apitesting.api;

import com.example.apitesting.config.RestAssuredConfig;
import com.example.apitesting.data.TestDataFactory;
import com.example.apitesting.models.*;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.apitesting.specs.Specifications;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API Тестирование: Создание администратора и обработка заявки")
@Feature("Позитивные сценарии")
public class ProcessRequestTests {

    @BeforeClass
    public void setup() {
        Allure.step("Настройка конфигурации RestAssured");
        RestAssuredConfig.setup();
    }

    @Test(description = "ZAJ-376: Создание администратора и обработка заявки")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение списка заявок после авторизации администратора")
    @Description("Тест получает список всех заявок")
    public void shouldCreateAdminAndProcessRequest() {
        Allure.step("1. Заполнение формы");
        SendAdminRequest adminRequest = SendAdminRequest.builder()
                .dateofbirth("1990-01-01")
                .personalFirstName("Admin")
                .personalLastName("Adminov")
                .personalMiddleName("Adminovich")
                .personalNumberOfPassport("ADMIN123")
                .personalPhoneNumber("80291112233")
                .build();
        Allure.step("2. Отправка запроса для создания админа");
        AdminResponse adminResponse = given()
                .spec(Specifications.userSpec())
                .body(adminRequest)
                .when()
                .post("/sendAdminRequest")
                .then()
                .statusCode(200)
                .extract()
                .as(AdminResponse.class);
        Allure.step("3. Создание админа");

        int staffId = adminResponse.getStaffid();
        System.out.println("Создание админа со staffid: " + staffId);
        Allure.step("4. Создание админа со staffid");
        SendUserRequest userRequest = TestDataFactory.createBirthRequest();

        ApplicationResponse createResponse = given()
                .spec(Specifications.userSpec())
                .body(userRequest)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(200)
                .extract()
                .as(ApplicationResponse.class);

        int applicationId = createResponse.getResponseData().getApplicationid();
        System.out.println("Создание заявки с ID: " + applicationId);
        Allure.step("5. Создание заявки с ID");
        RequestProcess processRequest = RequestProcess.builder()
                .applId(applicationId)
                .staffid(staffId)
                .action("approved")
                .build();

        given()
                .spec(Specifications.userSpec())
                .body(processRequest)
                .when()
                .post("/requestProcess")
                .then()
                .statusCode(200);

        System.out.println("Запрос успешен");

        ApplicationStatus statusResponse = given()
                .spec(Specifications.userSpec())
                .pathParam("applicationId", applicationId)
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(200)
                .extract()
                .as(ApplicationStatus.class);
        Allure.step("6. Проверка статуса заявки");
        assertThat(statusResponse.getData().getStatusofapplication())
                .as("Статус заявки должен быть approved")
                .isEqualTo("approved");

        System.out.println("Статус заявки: " + statusResponse.getData().getStatusofapplication());
    }
}