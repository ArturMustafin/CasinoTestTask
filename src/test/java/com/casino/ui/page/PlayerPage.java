package com.casino.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PlayerPage extends BasePage {
    public static HashMap<String, List<String>> headers = new HashMap<>();
    public static HashMap<String,String> mainMenu = new HashMap<>();
    public static HashMap<String,String> menuUsers = new HashMap<>();

    //TODO вопрос!!!!!!

    public PlayerPage(WebDriver chromeDriver) {
        super(chromeDriver);
        selectTitle();
        selectMainSideMenu();
        selectMenuUsers();
    }

    public PlayerPage mainMenuButtonClick(String s) {
        click(By.xpath(mainMenu.get(s)));
        return this;
    }

    public PlayerPage usersMenuButtonClick(String s) {
        click(By.xpath(menuUsers.get(s)));
        return this;
    }

    public String playersPageCorrect() {
        return chromeDriver.findElement(By.cssSelector("[class=\"panel-heading\"] strong")).getText();
    }

    public PlayerPage headersClick(String s) {
        click(By.xpath(headers.get(s).get(0)));
        return this;
    }

    // table

    //лист строк
    public List<WebElement> getRows() {
        waitVisibility(By.xpath("//tbody//tr"));
        return chromeDriver.findElements(By.xpath("//tbody//tr"));
    }

    //лист столбцов
    public List<String> getColumns(String s) {
        List<WebElement> rows = getRows();
        List<String> columns = new ArrayList<>();
        for (WebElement row : rows) {
            columns.add((row.findElement((By.xpath(".//td["+ headers.get(s).get(1)+"]"))).getText()));
        }
        return columns;
    }

    // headers таблицы
    public void selectTitle() {
        headers.put("Username", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c1\"]/a", "2"));
        headers.put("External ID", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c2\"]/a", "3"));
        headers.put("Name", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c3\"]/a", "4"));
        headers.put("Last name", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c4\"]/a", "5"));
        headers.put("E-mail", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c5\"]/a", "6"));
        headers.put("Phone", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c6\"]/a", "7"));
        headers.put("Hall", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c7\"]/a", "8"));
        headers.put("Registration date", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c9\"]/a", "10"));
        headers.put("Last visit", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c10\"]/a", "11"));
        headers.put("Verified player", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c12\"]/a", "13"));
        headers.put("Status", Arrays.asList("//*[@id=\"payment-system-transaction-grid_c14\"]/a", "15"));
    }

    public void selectMainSideMenu(){
        mainMenu.put("Dashboard","//*[@id=\"nav\"]/li[1]/a/span");
        mainMenu.put("Agent Info","//*[@id=\"nav\"]/li[2]/a/span");
        mainMenu.put("Settings","//*[@id=\"nav\"]/li[3]/a/span");
        mainMenu.put("Games","//*[@id=\"nav\"]/li[4]/a/span");
        mainMenu.put("Money","//*[@id=\"nav\"]/li[5]/a/span");
        mainMenu.put("Content","//*[@id=\"nav\"]/li[6]/a/span");
        mainMenu.put("SEO","//*[@id=\"nav\"]/li[7]/a/span");
        mainMenu.put("Users","//*[@id=\"nav\"]/li[8]/a/span");
        mainMenu.put("Bonuses","//*[@id=\"nav\"]/li[9]/a/span");
        mainMenu.put("Jackpots","//*[@id=\"nav\"]/li[10]/a/span");
        mainMenu.put("Messaging","//*[@id=\"nav\"]/li[11]/a/span");
        mainMenu.put("FAQ","//*[@id=\"nav\"]/li[12]/a/span");
        mainMenu.put("Shop","//*[@id=\"nav\"]/li[13]/a/span");
        mainMenu.put("Logs","//*[@id=\"nav\"]/li[14]/a/span");
        mainMenu.put("Reports","//*[@id=\"nav\"]/li[15]/a/span");
    }

    public void selectMenuUsers(){
        menuUsers.put("Players","//*[@id=\"s-menu-users\"]/li[1]/a/text()");
        menuUsers.put("Players registrations","//*[@id=\"s-menu-users\"]/li[2]/a/text()");
        menuUsers.put("Users","//*[@id=\"s-menu-users\"]/li[3]/a/text()");
        menuUsers.put("Devices","//*[@id=\"s-menu-users\"]/li[4]/a/text()");
        menuUsers.put("Blocked players","//*[@id=\"s-menu-users\"]/li[5]/a/text()");
    }
}

