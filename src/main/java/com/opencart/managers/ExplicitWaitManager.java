package com.opencart.managers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitManager {

    private static final Logger logger = LogManager.getLogger(ExplicitWaitManager.class);
    private static int explicitWaitTime = Integer.valueOf(ConfigReaderManager.getProperty("explicitWaiter"));

    private static WebDriverWait waitObject = new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(explicitWaitTime));

    public static void waitTillElementIsVisible(WebElement element) {

        logger.log(Level.DEBUG, "Wait till the element is visible has been started: " + element.getAccessibleName());
        waitObject.until(ExpectedConditions.visibilityOf(element));
        logger.log(Level.DEBUG, "Wait till the element is visible has been completed: " + element.getAccessibleName());
    }

    public static void waitTillElementIsClickable(WebElement element) {

        logger.log(Level.DEBUG, "Wait till the element is clickable has been started:" + element.getAccessibleName());
        waitObject.until(ExpectedConditions.elementToBeClickable(element));
        logger.log(Level.DEBUG, "Wait till the element is clickable has been completed: " + element.getAccessibleName());
    }
}
