package tests;

import config.RestAssuredConfig;
import data.TestDataFactory;
import models.SendUserRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import specs.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class CreateApplicationTests {

    @BeforeClass
    public void setup() {
        RestAssuredConfig.setup();
    }

    @Test(description = "ZAJ-239: Создание заявки на рождение (birth)")
    public void shouldCreateBirthApplication() {
        SendUserRequest request = TestDataFactory.createBirthRequest();

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
    public void shouldCreateMarriageApplication() {
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
    public void shouldCreateDeathApplication() {
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
    public void shouldFailWhenLastNameMissing() {
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