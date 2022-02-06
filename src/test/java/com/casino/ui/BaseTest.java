package com.casino.ui;

import com.casino.ui.page.Main;
import com.casino.ui.page.LoginPage;
import com.casino.ui.page.PlayerPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver chromeDriver;
    public Main main;
    public LoginPage loginPage;
    public PlayerPage playerPage;

    public void start() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //отключить все notifications в браузере
        options.addArguments("--disable-notifications");
        //отключить popup
        options.addArguments("--disable-popup-blocking");
        //не выводить инф. по popup
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

        chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        main = PageFactory.initElements(chromeDriver, Main.class);
        loginPage = PageFactory.initElements(chromeDriver, LoginPage.class);
        playerPage = PageFactory.initElements(chromeDriver, PlayerPage.class);
    }

    public void end() {
        chromeDriver.quit();
    }
}
