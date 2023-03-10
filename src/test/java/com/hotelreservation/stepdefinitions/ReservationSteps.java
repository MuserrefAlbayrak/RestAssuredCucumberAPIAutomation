package com.hotelreservation.stepdefinitions;

import com.hotelreservation.models.Booking;
import com.hotelreservation.models.BookingResponse;
import com.hotelreservation.models.Bookingdates;
import com.hotelreservation.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;
    Bookingdates bookingdates;
    Booking booking;
    @Given("user creates a new reservation")
    public void user_creates_a_new_reservation() {
        reservationService = new ReservationService();
    }
    @Given("user gives the information required for the reservation")
    public void user_gives_the_information_required_for_the_reservation() {
        authKey = reservationService.generateToken();

    }
    @When("user creates hotel reservation")
    public void user_creates_hotel_reservation() {
        bookingResponse = reservationService.generateBooking();

    }
    @Then("user verifies that the reservation was created successfully")
    public void user_verifies_that_the_reservation_was_created_successfully() {
        booking = new Booking();
        Assertions.assertEquals("QA", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Cucumber", bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1000, bookingResponse.getBooking().getTotalprice());
        Assertions.assertEquals(false,bookingResponse.getBooking().getDepositpaid());
        Assertions.assertEquals("2023-04-01",bookingResponse.getBooking().getBookingdates().getCheckin());
        Assertions.assertEquals("2023-05-01",bookingResponse.getBooking().getBookingdates().getCheckout());
        Assertions.assertEquals("JAVASCRIPT", bookingResponse.getBooking().getAdditionalneeds());

    }
    @Then("user deletes the created reservation")
    public void user_deletes_the_created_reservation() {
        reservationService.deleteReservation(authKey, bookingResponse.getBookingid());
    }
}
