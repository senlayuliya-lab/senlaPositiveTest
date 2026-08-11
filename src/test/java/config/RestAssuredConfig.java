package config;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class RestAssuredConfig {

    public static void setup() {
        RestAssured.baseURI = "https://regoffice.senla.eu";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}