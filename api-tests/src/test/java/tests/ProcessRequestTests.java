package tests;

import config.RestAssuredConfig;
import data.TestDataFactory;
import models.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import specs.Specifications;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ProcessRequestTests {

    @BeforeClass
    public void setup() {
        RestAssuredConfig.setup();
    }

    @Test(description = "Создание администратора и обработка заявки")
    public void shouldCreateAdminAndProcessRequest() {
        SendAdminRequest adminRequest = SendAdminRequest.builder()
                .dateofbirth("1990-01-01")
                .personalFirstName("Admin")
                .personalLastName("Adminov")
                .personalMiddleName("Adminovich")
                .personalNumberOfPassport("ADMIN123")
                .personalPhoneNumber("80291112233")
                .build();

        AdminResponse adminResponse = given()
                .spec(Specifications.userSpec())
                .body(adminRequest)
                .when()
                .post("/sendAdminRequest")
                .then()
                .statusCode(200)
                .extract()
                .as(AdminResponse.class);

        int staffId = adminResponse.getStaffid();
        System.out.println("Создание админа со staffid: " + staffId);

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

        assertThat(statusResponse.getData().getStatusofapplication())
                .as("Статус заявки должен быть approved")
                .isEqualTo("approved");

        System.out.println("Статус заявки: " + statusResponse.getData().getStatusofapplication());
    }
}