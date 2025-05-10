package com.opencart.stepdefinitions;

import com.opencart.managers.ConfigReaderManager;
import com.opencart.managers.DriverManager;
import com.opencart.managers.ExplicitWaitManager;
import com.opencart.managers.ScrollManager;
import com.opencart.pageobjects.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;

public class GenericSteps {

    private static final Logger logger = LogManager.getLogger(GenericSteps.class);
    WebDriver driver = DriverManager.getInstance().getDriver();

    @Then("the URL contains the following keyword {string}")
    public void theURLContainsTheFollowingKeyword(String collectKeyword) throws InterruptedException {

        Thread.sleep(1000);
        boolean containsKeyword = driver.getCurrentUrl().contains(collectKeyword);
        Assertions.assertTrue(containsKeyword, "The url contains the keyword" + collectKeyword);
    }

    @Given("The {string} endpoint is accessed")
    public void theIsAccessed(String endPoint) {

        String fullLink = ConfigReaderManager.getProperty("baseUrl") + endPoint;
        driver.get(fullLink);
        logger.log(Level.WARN, "The page " + fullLink + " has been accessed");
        System.out.println("The accessed link is: " + fullLink);
    }

    @And("a thread sleep of {int} seconds is executed")
    public void aThreadSleepOfSecondsIsExecuted(int timeToBeSlept) {

        try {
            Thread.sleep(timeToBeSlept * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the following list of error messages is displayed:")
    public void theFollowingListOfErrorMessagesIsDisplayed(List<String> listOfErrors) throws InterruptedException {

        Thread.sleep(500);
        listOfErrors.forEach(errorMessage -> {
            logger.log(Level.INFO, "The asserted message is: " + errorMessage);
            boolean messageIsDisplayed = driver.findElement(By.xpath(".//*[contains(text(),'" +
                    errorMessage + "')]")).isDisplayed();
            Assertions.assertTrue(messageIsDisplayed, "The [" + errorMessage + "error message is displayed");
        });
    }

    @When("The {string} from {string} is clicked")
    public void theFromIsClicked(String clickableElement, String pageName) throws Exception {

        Class classInstance = Class.forName("com.opencart.pageobjects." + pageName);
        Field webClickableElementField = classInstance.getDeclaredField(clickableElement);
        webClickableElementField.setAccessible(true);
        WebElement webClickableElement = (WebElement) webClickableElementField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
        ScrollManager.scrollToElement(webClickableElement);
        ExplicitWaitManager.waitTillElementIsClickable(webClickableElement);
        webClickableElement.click();
        logger.log(Level.INFO, "The button: " + clickableElement + " from page " + pageName + " has been clicked");
    }

    @And("The {string} from {string} is populated with {string}")
    public void theFromIsPopulatedWith(String elementName, String pageName, String value) {
        WebDriver driver = DriverManager.getInstance().getDriver();

        switch (pageName) {
            case "HomePage":
                HomePage homePage = new HomePage(driver);
                if (elementName.equalsIgnoreCase("searchInput")) {
                    homePage.enterSearchKeyword(value);
                } else {
                    throw new IllegalArgumentException("Element '" + elementName + "' is not defined for HomePage.");
                }
                break;
            // Adaugă aici alte cazuri, de ex. RegisterPage, LoginPage, etc.
            default:
                throw new IllegalArgumentException("Page '" + pageName + "' is not supported in this step definition.");
        }
    }
}