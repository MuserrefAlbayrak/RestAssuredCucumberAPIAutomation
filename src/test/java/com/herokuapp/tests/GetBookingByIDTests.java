package com.herokuapp.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIDTests extends TestBase {
    @Test
    public void getBookingByIdTest(){
        Integer reservationID = createBookingID();
       Response response = given(spec)
                .when()
                .get("/booking/" + reservationID);
       response
               .then()
               .statusCode(200);

       String firstname = response.jsonPath().getJsonObject("firstname");
       String lastname = response.jsonPath().getJsonObject("lastname");
       Integer totalprice = response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals("Agah",firstname);
        Assertions.assertEquals("Agaoglu",lastname);
        Assertions.assertEquals(981,totalprice);
    }
}
