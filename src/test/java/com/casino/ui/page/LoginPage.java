package com.casino.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {
    public LoginPage(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public LoginPage fillInLogin(String login) {
        writeText(By.cssSelector("[id=UserLogin_username]"), login);
        return this;
    }

    public LoginPage fillInPassword(String password) {
        writeText(By.cssSelector("[id=UserLogin_password]"), password);
        return this;
    }

    public void loginButtonClick() {
        click(By.cssSelector("input[value=\"Sign in\"]"));
    }

    public LoginPage checkAllElementsOnPagePresent() {
        isElementDisplayed(By.cssSelector("[id=UserLogin_username]"));
        isElementDisplayed(By.cssSelector("[id=UserLogin_password]"));
        isElementDisplayed(By.cssSelector("input[value=\"Sign in\"]"));
        return this;
    }

    public String getLogin() {
        return chromeDriver.findElement(By.cssSelector("[class=dropdown-toggle] span")).getText();
    }
}
