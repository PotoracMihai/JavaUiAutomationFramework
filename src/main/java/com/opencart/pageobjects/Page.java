package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    // Declara variabila driver ca protected pentru a putea fi folosită în clasele derivate
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver; // Inițializează driver-ul
        PageFactory.initElements(driver, this);
    }

    // Elementele comune
    @FindBy(xpath = "//i[@class='fa-solid fa-user']")
    protected WebElement myAccountIcon;

    @FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Register']")
    protected WebElement registerLink;

    @FindBy(css = "body > nav:nth-child(2) > div:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)")
    protected WebElement loginLink;

    public void navigateToRegisterPage() {
        myAccountIcon.click();
        registerLink.click();
        System.out.println("The register option was selected from Header");
    }

    public void navigateToLoginPage(){
        myAccountIcon.click();
        loginLink.click();
    }
}
