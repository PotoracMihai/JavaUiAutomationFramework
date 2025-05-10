package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Câmpul de căutare
    @FindBy(xpath = "//div[@id='search']//input[@name='search']")
    private WebElement searchInput;

    // Butonul de căutare
    @FindBy(xpath = "//div[@id='search']//button[@type='button']")
    private WebElement searchBtn;

    public void enterSearchKeyword(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
        System.out.println("Keyword introdus: " + keyword);
    }

    public void clickSearchButton() {
        searchBtn.click();
        System.out.println("Butonul de căutare a fost apăsat.");
    }

    // (opțional, dacă vrei versiunea combinată)
    public void searchForProduct(String keyword) {
        enterSearchKeyword(keyword);
        clickSearchButton();
    }
}
