package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.AccountPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.RegisterPage;
import org.openqa.selenium.WebDriver;

public class TestRunnerWithPageObjects {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://tekwillacademy-opencart.online/");

        HomePage homePage = new HomePage(driver);

        homePage.navigateToRegisterPage();

        //Generate random data
        String firstName = RandomDataManager.getRandomFirstName();
        String lastName = RandomDataManager.getRandomLastName();
        String password = RandomDataManager.getRandomPassword();
        String email = RandomDataManager.getRandomEmail();

        //Actions on the Register page
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.completeTheRegisterForm(firstName,lastName, email,password);
        registerPage.enableTheToggleBar();
        registerPage.clickOnTheContinueButton();


        AccountPage accountPage = new AccountPage(driver);
        accountPage.logOutFromTheAccount();

        homePage.navigateToLoginPage();
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.completeTheLoginForm(email,password);
        loginPage.clickOnTheLoginButton();

        Thread.sleep(5000);

        driver.quit();
    }
}
