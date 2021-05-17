package org.acme.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello Quarkus"));
    }

    @Test
    public void testGreetingEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
                .when().get("/hello/greeting/{name}")
                .then()
                .statusCode(200)
                .body(is("Hello " + uuid));
    }

    @Test
    public void getCarTestEndpoint() {
        given()
                .when().get("/hello/car")
                .then()
                .statusCode(200)
                .assertThat()
                .body("brand", equalTo("Volkswagen"))
                .body("model", equalTo("Golf G60"))
                .body("power", equalTo(180));
    }

    @Test
    public void createCarTestEndpoint() {
        Car postCarTest = new Car("BMW", "e30", 200);
        given()
                .contentType("application/json")
                .body(postCarTest)
                .when().post("/hello/car")
                .then()
                .statusCode(200)
                .assertThat()
                .body("brand", equalTo(postCarTest.getBrand()))
                .body("model", equalTo(postCarTest.getModel()))
                .body("power", equalTo(postCarTest.getPower()));
    }

}