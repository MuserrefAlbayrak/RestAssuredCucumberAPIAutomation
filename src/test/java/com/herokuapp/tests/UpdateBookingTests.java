package com.herokuapp.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends TestBase{

    @Test
    public void updateBookingTest(){
                /*
                curl -X PUT \
          https://restful-booker.herokuapp.com/booking/1 \
          -H 'Content-Type: application/json' \
          -H 'Accept: application/json' \
          -H 'Cookie: token=abc123' \ ---->> TOKEN OLUSTURMALISIN MUSERREF
          -d '{
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }'
                 */

        Integer bookingID = createBookingID();
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + createToken())
                .body(bookingObject("Asiye","Yildiz",123,false))
                .put("/booking/" + bookingID);
        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        Integer totalprice = response.jsonPath().getJsonObject("totalprice");
        Boolean depositpaid = response.jsonPath().getJsonObject("depositpaid");
        Assertions.assertEquals("Asiye",firstname);
        Assertions.assertEquals("Yildiz",lastname);
        Assertions.assertEquals(123,totalprice);
        Assertions.assertEquals(false,depositpaid);
    }
}
