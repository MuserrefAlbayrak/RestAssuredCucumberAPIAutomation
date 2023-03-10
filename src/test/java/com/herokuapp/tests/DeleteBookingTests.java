package com.herokuapp.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteBookingTests extends TestBase{
    @Test
    public void deleteBookingTest(){
        Response response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .when()
                .delete("/booking/" + createBookingID());
        response
                .then()
                        .statusCode(201);
        Assertions.assertEquals(201, response.statusCode());
    }
}
