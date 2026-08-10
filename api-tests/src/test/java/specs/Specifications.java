package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    public static RequestSpecification userSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://regoffice.senla.eu")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Basic dXNlcjpzZW5sYXRlc3Q=")  // user/senlatest
                .build();
    }

    public static RequestSpecification adminSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://regoffice.senla.eu")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Basic YWRtaW46c2VubGF0ZXN0")  // admin/senlatest
                .build();
    }
}