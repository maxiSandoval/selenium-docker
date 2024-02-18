package com.maaxii.pages.vendorportal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maaxii.pages.AbstractPage;

public class DashboardPage extends AbstractPage {

private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;

    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultCountElement;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;

    /*
     * prefer id / name / css
     */
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
        return monthlyEarningElement.isDisplayed();
    }

    public String getMonthlyEarning() {
        return monthlyEarningElement.getText();
    }

    public String getAnnualEarning() {
        return annualEarningElement.getText();
    }

    public String getProfitMargin() {
        return profitMarginElement.getText();
    }

    public String getAvailableInventory() {
        return availableInventoryElement.getText();
    }

    public void searchOrderHistory(String keyword){
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultCount(){
        String resultsText = this.searchResultCountElement.getText();
        String[] arr = resultsText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }

    public void logout(){
        this.userProfilePictureElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }
}
