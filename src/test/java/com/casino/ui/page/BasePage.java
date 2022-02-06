package com.casino.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class BasePage {
    public WebDriver chromeDriver;
    public WebDriverWait wait;


    public BasePage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        wait = new WebDriverWait(chromeDriver, 10);
    }

    // Wait Wrapper Method  by element located By
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    // Click Method by element located By
    public void click(By elementBy) {
        waitVisibility(elementBy);
        chromeDriver.findElement(elementBy).click();
    }

    //Is Element located By  Displayed
    public void isElementDisplayed(By elementBy) {
        waitVisibility(elementBy);
        assertTrue(chromeDriver.findElement(elementBy).isDisplayed());
    }

    //Write Text in field located By
    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        WebElement element = chromeDriver.findElement(elementBy);
        element.clear();
        element.sendKeys(text);
    }
}
