package com.herokuapp.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartialUpdateBookingTests extends TestBase{
    @Test
    public void partialUpdateBookingTest(){

        Integer bookingID = createBookingID();
        JSONObject body = new JSONObject();
        body.put("firstname","Sevgi");
        body.put("lastname","Yilmaz");
        Response response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" + bookingID);

        Assertions.assertEquals("Sevgi",response.jsonPath().getJsonObject("firstname"));
        Assertions.assertEquals("Yilmaz",response.jsonPath().getJsonObject("lastname"));

    }
}
