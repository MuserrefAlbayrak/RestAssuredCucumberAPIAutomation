package com.herokuapp.tests;

import com.herokuapp.modelss.Booking;
import com.herokuapp.modelss.BookingResponse;
import com.herokuapp.modelss.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends TestBase {
    /*
            curl -X POST \
          https://restful-booker.herokuapp.com/booking \
          -H 'Content-Type: application/json' \
          -d '{
            "firstname" : "Jim",
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

    @Test
    public void createBookingTest(){
       Response response = createBooking();

        Assertions.assertEquals("Agah",response
                .jsonPath()
                .getJsonObject("booking.firstname"));
        Assertions.assertEquals("Agaoglu",response
                .jsonPath()
                .getJsonObject("booking.lastname"));
        Assertions.assertEquals(981,(Integer)response
                .jsonPath()
                .getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response
                .jsonPath()
                .getJsonObject("booking.depositpaid"));
    }
    @Test
    public void createBookingWithPojoTest(){
        Bookingdates bookingdates = new Bookingdates("2022-10-27",
                "2023-10-03");
        Booking booking = new Booking("Muserref",
                "Albayrak",
                777,
                true,
                bookingdates,
                "Apricot");
        Response response = RestAssured
                .given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when().post("/booking");
        response
                .then()
                .statusCode(200);
        BookingResponse bookingResponse = response.as(BookingResponse.class);//De-Serialization
        System.out.println(bookingResponse);
        Assertions.assertEquals(booking.getFirstname(),bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals(booking.getLastname(),bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(booking.getTotalprice(),bookingResponse.getBooking().getTotalprice());
        Assertions.assertEquals(booking.getDepositpaid(),bookingResponse.getBooking().getDepositpaid());
        Assertions.assertEquals(booking.getAdditionalneeds(),bookingResponse.getBooking().getAdditionalneeds());
        Assertions.assertEquals(bookingdates.getCheckin(),bookingResponse.getBooking().getBookingdates().getCheckin());
        Assertions.assertEquals(bookingdates.getCheckout(),bookingResponse.getBooking().getBookingdates().getCheckout());
    }
}
