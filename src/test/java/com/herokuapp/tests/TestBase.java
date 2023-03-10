package com.herokuapp.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class TestBase {
    RequestSpecification spec;
    @BeforeEach
    public void setUp(){
                spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter())) //addFilters() methodu ile request response ozelinde belirli islemler gerceklestirebiliriz.
                .build();                                                         // RequestLoggingFilter ve ResponseLoggingFilter  bu classlar ile de yaptigimiz cagrilari loglayabiliriz
                                                                                 //response.prettyPrint() ve log().all() tekrar tekrar yazmaya gerek kalmiyor.
    }
    protected String createToken(){
        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("/auth");

        return response.jsonPath().getJsonObject("token");
    }
    protected Integer createBookingID(){
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }
    protected Response createBooking(){
        Response response = RestAssured
                .given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Agah","Agaoglu",981,true))
                .post("/booking");

        response
                .then()
                .statusCode(200);
        return response;
    }
    protected String bookingObject(String firstname, String lastname, Integer totalprice, Boolean depositpaid){
        JSONObject body = new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname",lastname);
        body.put("totalprice",totalprice);
        body.put("depositpaid",depositpaid);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2022-02-02");
        bookingDates.put("checkout","2023-02-02");
        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Badem");
        return body.toString();
    }
}
