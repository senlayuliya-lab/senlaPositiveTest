package com.example.apitesting.bdd.stepdefinitions;

import com.example.apitesting.data.TestDataFactory;
import com.example.apitesting.models.ApplicationResponse;
import com.example.apitesting.models.SendUserRequest;
import com.example.apitesting.specs.Specifications;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ApplicationSteps {
    private static final Logger log = LogManager.getLogger(ApplicationSteps.class);

    private Response response;
    private SendUserRequest request;
    private int applicationId;

    @Given("I prepare data for birth application")
    public void prepareBirthData() {
        log.info("Preparing data for birth application");
        request = TestDataFactory.createBirthRequest();
        log.debug("Data prepared: mode={}", request.getMode());
    }

    @Given("I prepare data without last name")
    public void prepareDataWithoutLastName() {
        log.info("Preparing data without last name (negative test)");
        request = TestDataFactory.createRequestWithoutLastName();
        log.debug("Data prepared");
    }

    @When("I send request to create application")
    public void sendCreateRequest() {
        log.info("Sending request to create application");
        response = given()
                .spec(Specifications.userSpec())
                .body(request)
                .post("/sendUserRequest");
        log.info("Response status: {}", response.getStatusCode());
    }

    @Then("response status is {int}")
    public void checkStatus(int expected) {
        log.info("Checking response status: expected {}", expected);
        Assert.assertEquals(response.getStatusCode(), expected);
        log.info("Status matched: {}", expected);
    }

    @Then("response has application ID greater than 0")
    public void checkApplicationId() {
        log.info("Checking application ID");
        ApplicationResponse appResponse = response.as(ApplicationResponse.class);
        applicationId = appResponse.getResponseData().getApplicationid();
        Assert.assertTrue(applicationId > 0);
        log.info("Application ID: {}", applicationId);
    }

    @Then("application type in response is {string}")
    public void checkApplicationMode(String expected) {
        log.info("Checking application type: {}", expected);
        Assert.assertEquals(request.getMode(), expected);
        log.info("Type matched: {}", expected);
    }
}