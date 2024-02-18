package com.maaxii.tests.flightreservation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.maaxii.pages.flightreservation.FlightsConfirmationPage;
import com.maaxii.pages.flightreservation.FlightsSearchPage;
import com.maaxii.pages.flightreservation.FlightsSelectionPage;
import com.maaxii.pages.flightreservation.RegistrationConfirmationPage;
import com.maaxii.pages.flightreservation.RegistrationPage;
import com.maaxii.tests.AbstractTest;
import com.maaxii.tests.flightreservation.model.FlightReservationTestData;
import com.maaxii.util.Config;
import com.maaxii.util.Constants;
import com.maaxii.util.JsonUtil;

public class FlightReservationTests extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddres(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.passengersCount());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest() {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTests() {
        FlightsConfirmationPage flightsConfirmationPage = new FlightsConfirmationPage(driver);
        Assert.assertTrue(flightsConfirmationPage.isAt());
        Assert.assertEquals(flightsConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
