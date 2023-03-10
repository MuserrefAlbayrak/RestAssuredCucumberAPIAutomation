package com.hotelreservation.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;

public class TestBase {
     RequestSpecification spec;
    public TestBase(){
                spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter())) //addFilters() methodu ile request response ozelinde belirli islemler gerceklestirebiliriz.
                .build();                                                         // RequestLoggingFilter ve ResponseLoggingFilter  bu classlar ile de yaptigimiz cagrilari loglayabiliriz
                                                                                 //response.prettyPrint() ve log().all() tekrar tekrar yazmaya gerek kalmiyor.
    }

}
