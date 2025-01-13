package com.opencart.pageobjects;

import com.opencart.managers.ScrollManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page {

    private static final Logger logger = LogManager.getLogger(RegisterPage.class);

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    private WebElement fisrtNameInput;
    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;
    @FindBy(id = "input-email")
    private WebElement emailInput;
    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;
    @FindBy(name = "agree")
    private WebElement privacyToggleBar;
    @FindBy(css = "button[type='submit']")
    private WebElement continueBtn;

    public void completeTheRegisterForm(String firstName, String lastName, String email, String password) {
        fisrtNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        logger.log(Level.INFO, "The email is: " + email + " and the password is: " + password);
    }

    public void enableTheToggleBar() {
        ScrollManager.scrollToElement(privacyToggleBar);
        privacyToggleBar.click();
    }

    public void clickOnTheContinueButton() {
        ScrollManager.scrollToElement(continueBtn);
        continueBtn.click();
    }

}
