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
import java.util.Objects;

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
        Objects.requireNonNull(basicToken, "basicToken must not be null");
        log.info("Получен Authorization : Basic {}", basicToken);
        PostGuestRequest bodyGuest = new PostGuestRequest("client_credentials", "guest:default");
        responseGuest = (PostGuestResponse) casinoService.getToken(basicToken, bodyGuest, PostGuestResponse.class);
        log.info("response: {}", responseGuest);
        tokenGuest = responseGuest.getAccessToken();
        log.info("Получен токен гостя: {}", responseGuest.getAccessToken());
    }

    @Then("Гость авторизован")
    public void guestLogInStep() {
        assertEquals("Bearer", responseGuest.getTokenType());
        Assertions.assertNotNull(responseGuest.getAccessToken());
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
        log.info("request body: {}", bodyRegisterPlayer);
        responseRegisterPlayer = casinoService.registerPlayer(tokenGuest, bodyRegisterPlayer);
        log.info("response body: {}", responseRegisterPlayer);
        log.info("Новый пользователь зарегистрирован: {}", responseRegisterPlayer.getUsername());
    }

    @Then("Игрок зарегистрирован")
    public void checkPlayerStep() {
        Assertions.assertEquals(userNamePlayer, responseRegisterPlayer.getUsername());
    }

    @When("Авторизоваться под созданным игроком")
    public void logInPlayerStep() {
        PostLogInCreatedPlayerRequest bodyLogIn = new PostLogInCreatedPlayerRequest(
                "password", userNamePlayer, passBasePlayer);
        log.info("request body: {}", bodyLogIn);
        responseLogIn = (PostLogInCreatedPlayerResponse) casinoService.getToken(basicToken, bodyLogIn, PostLogInCreatedPlayerResponse.class);
        log.info("response body: {}", responseLogIn);
        log.info("Пользователь: {}, авторизовался.", userNamePlayer);
        token = responseLogIn.getAccessToken();
    }

    @Then("Игрок авторизован")
    public void authorizedPlayerStep() {
        assertEquals("Bearer", responseLogIn.getTokenType());
        Assertions.assertNotNull(responseLogIn.getAccessToken());
    }

    @When("Запросить данные профиля игрока")
    public void infoPlayerStep() {
        responseInfoPlayer = casinoService.getInfoPlayer(responseRegisterPlayer.getId(), token);
        log.info("response body: {}", responseInfoPlayer);
    }

    @Then("Найден игрок")
    public void playerFoundStep() {
        Assertions.assertEquals(responseRegisterPlayer.getId(), responseInfoPlayer.getId());
        Assertions.assertEquals(responseRegisterPlayer.getEmail(), responseInfoPlayer.getEmail());
    }

    @When("Запросить данные другого игрока")
    public void infoOtherPlayerStep() {
        responseInfoOtherPlayer = casinoService.getInfoOtherPlayer(352531, token);
        log.error("response body: {}", responseInfoOtherPlayer);
    }

    @Then("Игрок не найден")
    public void playerNotFoundStep() {
        Assertions.assertEquals(404, responseInfoOtherPlayer.getStatus());
    }

    private static String getBase64Encoder(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }
}
