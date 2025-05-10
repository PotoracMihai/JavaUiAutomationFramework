package com.opencart.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchPage extends Page {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // Rezultatele căutării (produse)
    @FindBy(css = "#content .product-thumb")
    private List<WebElement> searchResults;

    // Actualizat: Mesajul de eroare când nu există rezultate
    // Se presupune că mesajul este afișat într-un <p> din interiorul unui element cu clasa "col"
    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    private WebElement noResultsMessage;

    // Așteaptă până când cel puțin un element din lista de produse este vizibil sau 10 secunde
    public boolean areResultsDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
            return searchResults != null && !searchResults.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // Așteaptă până când mesajul no-results este vizibil
    public boolean isNoResultsMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getNumberOfResults() {
        return searchResults.size();
    }

    // Metodă pentru a extrage textul mesajului de eroare/no-results
    public String getNoResultsMessageText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
            return noResultsMessage.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // Metodă pentru verificarea titlurilor produselor (dacă este necesar)
    public boolean doSearchResultsContainKeyword(String keyword) {
        if (!areResultsDisplayed()) {
            return false;
        }
        for (WebElement product : searchResults) {
            try {
                // Presupunem că titlul produsului se află într-un element <h4><a> în cadrul fiecărui product-thumb
                WebElement titleElement = product.findElement(By.cssSelector("h4 a"));
                String titleText = titleElement.getText();
                if (titleText.toLowerCase().contains(keyword.toLowerCase())) {
                    return true;
                }
            } catch (Exception e) {
                // Dacă titlul nu este găsit pentru un produs, continuăm cu următorul
            }
        }
        return false;
    }
}
