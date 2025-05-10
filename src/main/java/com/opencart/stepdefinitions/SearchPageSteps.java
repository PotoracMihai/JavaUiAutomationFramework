package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class SearchPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);

    @And("The search input from HomePage is populated with {string}")
    public void theSearchInputFromHomePageIsPopulatedWith(String keyword) {
        homePage.searchForProduct(keyword);
    }

    @Then("The search results contain at least one product with title containing {string}")
    public void theSearchResultsContainAtLeastOneProductWithTitleContaining(String keyword) {
        Assert.assertTrue("No search results were found for: " + keyword,
                searchPage.doSearchResultsContainKeyword(keyword));
    }

    @Then("The message {string} is displayed")
    public void theMessageIsDisplayed(String expectedMessage) {
        String actualMessage = searchPage.getNoResultsMessageText();
        Assert.assertEquals("There is no product that matches the search criteria.", expectedMessage, actualMessage);
    }


}
