package com.opencart.pageobjects;

import com.opencart.managers.ScrollManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends Page{

    public AccountPage (WebDriver driver){
        super(driver);
    }

    @FindBy(css = "aside[id='column-right'] a:nth-child(1)")
    private WebElement logOutBtn;

    public void logOutFromTheAccount(){
        ScrollManager.scrollToElement(logOutBtn);
        logOutBtn.click();
        System.out.println("The user has been logged out from the account");
    }
}
