package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.sql.Driver;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://google.com/");

        String theNameOfTheFirstTab = driver.getWindowHandle();

        //Open a new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://diez.md/");
        Thread.sleep(2000);
        //Close the new tab
        driver.close();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://stiri.md/");
        Thread.sleep(2000);
        //Close the new tab
        driver.close();
    }
}