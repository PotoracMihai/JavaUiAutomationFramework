package com.opencart.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {

    private static DriverManager instance;
    private WebDriver driver;
    private static final String WEB_DRIVER_TYPE = ConfigReaderManager.getProperty("browserType");

    public DriverManager() {

        switch (WEB_DRIVER_TYPE.toUpperCase()) {
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                System.out.println("Chrome driver is opened");
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                System.out.println("Firefox driver is opened");
                break;
            case "Edge":
                driver = new EdgeDriver();
                System.out.println("Edge driver is opened");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                System.out.println("Safari driver is opened");
                break;

            default:
                System.out.println("The Browser type is not defined: " + WEB_DRIVER_TYPE);
        }
        int implicitWaitTime = Integer.valueOf(ConfigReaderManager.getProperty("implicitWaitTime"));
        int pageLoadTime = Integer.valueOf(ConfigReaderManager.getProperty("pageLoadTime"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public static DriverManager getInstance() {

        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {

        return driver;
    }

    public void quiteDriver() {

        driver.quit();
        driver = null;
        instance = null;
        System.out.println("The driver is closed after running and completing the test scenario");
    }
}
