package com.maaxii.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.maaxii.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

  @FindBy(id = "go-to-flights-search")
  private WebElement goToFlightSearchButton;

  @FindBy(css = "#registration-confirmation-section p b")
  private WebElement firstNameElement;

  public RegistrationConfirmationPage(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean isAt() {
    this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
    return goToFlightSearchButton.isDisplayed();
  }

  public void goToFlightSearch() {
    this.goToFlightSearchButton.click();
  }

  public String getFirstName(){
    return this.firstNameElement.getText();
  }
}
