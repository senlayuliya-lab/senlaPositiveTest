package tests;

import config.RestAssuredConfig;
import data.TestDataFactory;
import models.ApplicationResponse;
import models.ApplicationStatus;
import models.SendUserRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import specs.Specifications;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetStatusTests {

    @BeforeClass
    public void setup() {
        RestAssuredConfig.setup();
    }

    @Test(description = "ZAJ-272: Получение статуса заявки")
    public void shouldGetApplicationStatus() {
        // 1. Создаем заявку
        SendUserRequest request = TestDataFactory.createBirthRequest();

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

        assertThat(statusResponse).isNotNull();
        assertThat(statusResponse.getStatusofapplication())
                .as("Статус должен быть указан")
                .isNotNull()
                .isNotBlank();

        System.out.println("Application status: " + statusResponse.getStatusofapplication());
        System.out.println("Application kind: " + statusResponse.getKindofapplication());
    }
    @Test(description = "ZAJ-306: Получение списка всех заявок")
    public void shouldGetApplicationsList() {
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