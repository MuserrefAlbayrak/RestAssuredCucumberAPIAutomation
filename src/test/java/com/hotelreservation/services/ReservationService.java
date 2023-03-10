package com.hotelreservation.services;

import com.hotelreservation.models.Auth;
import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingResponse;
import com.hotelreservation.models.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends TestBase{
    public String generateToken(){
        Auth auth = new Auth("admin","password123");
        Response response = given().spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(auth)
                .post("/auth");
        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }
    public BookingResponse generateBooking() {
        Bookingdates bookingdates = new Bookingdates("2023-04-01", "2023-05-01");
        Booking booking = new Booking("QA", "Cucumber", 1000, false, bookingdates, "JAVASCRIPT");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteReservation(String token, int bookingId) {
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);
    }

}
