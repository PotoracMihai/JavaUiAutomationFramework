package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegisterFlowWithJUnit {

    private WebDriver driver;
    private HomePage homePage;

    private RegisterPage registerPage;
    @BeforeAll
    public static void beforeAllTheTest(){
        System.out.println("The methods is run before all the tests from this class");
    }

    @BeforeEach
    public void beforeEachEachTest(){
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");

        homePage = new HomePage(driver);

        homePage.navigateToRegisterPage();
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("User is redirected to account page when registering with valid data")
    public void registerFlowWithValidDataRediracectsTheUserToAccountPage() throws InterruptedException {


        //Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        //Actions on the Register page
        registerPage.completeTheRegisterForm(firstName, lastName, email, password);
        registerPage.enableTheToggleBar();
        registerPage.clickOnTheContinueButton();

        Thread.sleep(2000);

        boolean urlContains = driver.getCurrentUrl().contains("success");
        Assertions.assertTrue(urlContains, "The URL of the page contain the Succes keyword");

        DriverManager.getInstance().quiteDriver();
    }

    @Test
    @DisplayName("Navigate to Login page from Register page")
    public void navigateToLoginPageFromRegisterPage(){
        registerPage.navigateToLoginPage();

            Assertions.assertTrue(driver.getCurrentUrl().contains("login"), "The page url has the keyword login");
    }

    @Test
    @DisplayName("The user remains on the Register page when registering without accepting without accepting the terms and conditions")
    public void userRemainOnRegisterPageWhenRegisteringWithoutAcceptingPrivacyRules() throws InterruptedException {

        //Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        //Actions on the Register page
        registerPage.completeTheRegisterForm(firstName,lastName, email,password);
        registerPage.clickOnTheContinueButton();

        Assertions.assertTrue(driver.getCurrentUrl().contains("register"), "The page url has the keyword register");

        DriverManager.getInstance().quiteDriver();
    }

    @AfterEach
    public void afterEachTheTest(){
        DriverManager.getInstance().quiteDriver();
    }

    @AfterAll
    public static void afterAllTheTest(){
        System.out.println("The methods is executed after all the tests");
    }
}
