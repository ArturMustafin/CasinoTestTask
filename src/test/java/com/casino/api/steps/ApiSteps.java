package com.casino.api.steps;

import com.casino.api.services.CasinoService;
import com.casino.dto.*;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ApiSteps {
    private final CasinoService casinoService = new CasinoService();
    private static final Faker faker = new Faker();
    private PostGuestResponse responseGuest;
    private PostRegisterPlayerResponse responseRegisterPlayer;
    private PostLogInCreatedPlayerResponse responseLogIn;
    private GetInfoPlayerResponse responseInfoPlayer;
    private GetNotFoundResponse responseInfoOtherPlayer;
    private String tokenGuest;
    private String token;
    private static String userNamePlayer;
    private static String passBasePlayer;
    private String basicToken;

    @When("Получить токен гостя username: {}, password: {}")
    public void getTokenGuestStep(String username, String password) {
        basicToken = getBase64Encoder(username + ":" + password);
        PostGuestRequest credentials = new PostGuestRequest("client_credentials", "guest:default");
        responseGuest = casinoService.getTokenGuest(basicToken, credentials);
        tokenGuest = responseGuest.getAccessToken();
    }

    @Then("Гость авторизован")
    public void guestLogInStep() {
        assertEquals("Bearer", responseGuest.getTokenType());
        Assertions.assertNotNull(responseGuest.getAccessToken());
        log.info("Получен токен гостя: {}", responseGuest);
    }

    @When("Зарегистрировать игрока")
    public void registerPlayerStep() {
        userNamePlayer = "auto" + RandomStringUtils.randomAlphabetic(7);
        passBasePlayer = getBase64Encoder(faker.name().firstName() + "test");
        PostRegisterPlayerRequest bodyRegisterPlayer = new PostRegisterPlayerRequest(
                userNamePlayer,
                passBasePlayer,
                passBasePlayer,
                faker.internet().emailAddress());
        responseRegisterPlayer = casinoService.registerPlayer(tokenGuest, bodyRegisterPlayer);
    }

    @Then("Игрок зарегистрирован")
    public void checkPlayerStep() {
        Assertions.assertEquals(userNamePlayer, responseRegisterPlayer.getUsername());
        log.info("Новый пользователь зарегистрирован: {}", responseRegisterPlayer);
    }

    @When("Авторизоваться под созданным игроком")
    public void logInPlayerStep() {
        PostLogInCreatedPlayerRequest bodyLogIn = new PostLogInCreatedPlayerRequest(
                "password", userNamePlayer, passBasePlayer);
        responseLogIn = casinoService.logInCreatedPlayer(basicToken, bodyLogIn);
        token = responseLogIn.getAccessToken();
    }

    @Then("Игрок авторизован")
    public void authorizedPlayerStep() {
        assertEquals("Bearer", responseLogIn.getTokenType());
        Assertions.assertNotNull(responseLogIn.getAccessToken());
        log.info("Пользователь: {}, авторизовался.", userNamePlayer);
    }

    @When("Запросить данные профиля игрока")
    public void infoPlayerStep() {
        responseInfoPlayer = casinoService.getInfoPlayer(responseRegisterPlayer.getId(), token);
    }

    @Then("Найден игрок")
    public void playerFoundStep() {
        Assertions.assertEquals(responseRegisterPlayer.getId(), responseInfoPlayer.getId());
        Assertions.assertEquals(responseRegisterPlayer.getEmail(), responseInfoPlayer.getEmail());
    }

    @When("Запросить данные другого игрока")
    public void infoOtherPlayerStep() {
        responseInfoOtherPlayer = casinoService.getInfoOtherPlayer(352531, token);
    }

    @Then("Игрок не найден")
    public void playerNotFoundStep() {
        Assertions.assertEquals(404, responseInfoOtherPlayer.getStatus());
    }

    private static String getBase64Encoder(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }
}
