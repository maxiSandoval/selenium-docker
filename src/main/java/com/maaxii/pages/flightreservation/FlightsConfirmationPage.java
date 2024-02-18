package com.maaxii.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maaxii.pages.AbstractPage;

public class FlightsConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightsConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement FlightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    public FlightsConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.totalPriceElement));
        return this.totalPriceElement.isDisplayed();
    }

    public String getPrice(){
        String confirmation = this.FlightConfirmationElement.getText();
        String price = this.totalPriceElement.getText();
        log.info("flight confirmation # : {}", confirmation);
        log.info("Total price : {}", price);
        return price;
    }

}
