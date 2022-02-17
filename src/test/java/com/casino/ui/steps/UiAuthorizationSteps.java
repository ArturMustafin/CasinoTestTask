package com.casino.ui.steps;

import com.casino.ui.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.*;

public class UiAuthorizationSteps extends BaseTest {
    private List<String> result;
    private List<String> sort;

    @Before
    public void initialization() {
        start();
    }

    @After
    public void teardown() {
        end();
    }

    @Given("открыть главную страницу")
    public void openLoginUrl() {
        main.goToLoginPage();
    }

    @When("ввести login {} и password {}")
    public void authorization(String login, String password) {
        loginPage.checkAllElementsOnPagePresent()
                .fillInLogin(login)
                .fillInPassword(password)
                .loginButtonClick();
    }

    @Then("пользователь {} успешно авторизован")
    public void assertLogin(String elementLogin) {
        Assert.assertEquals(elementLogin, loginPage.getLogin());
    }

    @When("нажать на выпадающее меню {}, перейти в {}")
    public void openPagePlayers(String mainMenu, String mainUser) {
        playerPage.mainMenuButtonClick(mainMenu)
                .usersMenuButtonClick(mainUser);
    }

    @Then("успешно открылась страница {}")
    public void assertPagePlayers(String elementPlayers) {
        Assert.assertEquals(elementPlayers, playerPage.playersPageCorrect());
    }

    @When("^выбрать \"([^\"]*)\"$")
    public void selectColumn(String column) throws InterruptedException {
        playerPage.headersClick(column);
        Thread.sleep(5000);
        result = playerPage.getColumns(column);
        sort = result;
        Collections.sort(result);
    }

    @Then("таблица верно отсортирована по выбранному столбцу")
    public void checkSortColumn() {
        Assert.assertEquals(result, sort);
    }
}
