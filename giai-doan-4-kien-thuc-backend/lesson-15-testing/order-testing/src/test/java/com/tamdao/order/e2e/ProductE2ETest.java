package com.tamdao.order.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductE2ETest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port=8080;
    }

    @Test
    void testGetAllOrders_success() {
        given()
                .when()
                .get("/api/orders")
                .then()
                .statusCode(200)
                .body("data", notNullValue())
                .body("data.size()",greaterThanOrEqualTo(0));
    }
}
