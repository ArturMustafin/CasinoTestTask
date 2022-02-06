package com.casino.ui.page;

import org.openqa.selenium.WebDriver;

public class Main extends BasePage {
    private final String URL_LOGIN = "http://test-app.d6.dev.devcaz.com/admin/login";

    public Main(WebDriver chromeDriver) {
        super(chromeDriver);
    }

    public Main goTo() {
        chromeDriver.get(URL_LOGIN);
        return this;
    }
}
