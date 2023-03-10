package com.herokuapp.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetAllBookingsTests extends TestBase{
    //curl -i https://restful-booker.herokuapp.com/booking
    @Test
    public void getAllBookingsTest(){
        RestAssured
                .given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }
    @Test
    public void getBookingsWithFirstnameLastnameFiltersTest(){
        Integer bookingID = createBookingID();
        spec.queryParams("firstname","Agah","lastname","Agaoglu");
        Response response = RestAssured
                .given(spec)
                .when()
                .get("/booking");
        response
                .then()
                .statusCode(200);
        List<Integer> list = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(list.contains(bookingID));
    }
}
