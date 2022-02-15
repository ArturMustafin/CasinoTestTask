package com.casino.api.steps;

import com.casino.ProjectConfig;
import com.casino.api.conditions.BodyFieldCondition;
import com.casino.api.conditions.Conditions;
import com.casino.api.services.UserApiService;
import com.casino.dto.*;
import com.casino.response.*;
import com.github.javafaker.Faker;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import static com.casino.api.conditions.Conditions.statusCode;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ApiSteps {
    private final UserApiService userApiService = new UserApiService();
    private static final Faker faker = new Faker();
    private GuestResponse responseGuest;
    private RegisterPlayerResponse responseRegisterPlayer;
    private PostLogInCreatedPlayerResponse responseLogIn;
    private GetInfoPlayerResponse responseInfoPlayer;
    private GetNotFoundResponse responseInfoOtherPlayer;
    private String tokenGuest;
    private String token;
    private static String userNamePlayer;
    private static String passBasePlayer;
    private String basicToken;

    @Before(order = 1)
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        RestAssured.baseURI = config.baseUrl();
    }

    @When("Получить токен гостя username: {}, password: {}")
    public void getTokenGuestStep(String username, String password) {
        basicToken = getBase64Encoder(username + ":" + password);
        Objects.requireNonNull(basicToken, "basicToken must not be null");
        log.info("Получен Authorization : Basic {}", basicToken);
        PostGuestRequest bodyGuest = new PostGuestRequest()
                .grantType("client_credentials")
                .scope("guest:default");

        responseGuest = userApiService.getToken(basicToken, bodyGuest)
                .shouldHave(statusCode(200))
                .shouldHave(Conditions.bodyField("access_token", notNullValue()))
                .asPojo(GuestResponse.class);

        log.info("response: {}", responseGuest);
        tokenGuest = responseGuest.getAccessToken();
        log.info("Получен токен гостя: {}", tokenGuest);
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
        PostRegisterPlayerRequest bodyRegisterPlayer = new PostRegisterPlayerRequest()
                .username(userNamePlayer)
                .passwordChange(passBasePlayer)
                .passwordRepeat(passBasePlayer)
                .email(faker.internet().emailAddress());

        responseRegisterPlayer = userApiService.registerPlayer(tokenGuest, bodyRegisterPlayer)
                .shouldHave(statusCode(201))
                .asPojo(RegisterPlayerResponse.class);
        log.info("response body: {}", responseRegisterPlayer);
        log.info("Новый пользователь зарегистрирован: {}", userNamePlayer);
    }

    @Then("Игрок зарегистрирован")
    public void checkPlayerStep() {
        Assertions.assertEquals(userNamePlayer, responseRegisterPlayer.getUsername());
    }

    @When("Авторизоваться под созданным игроком")
    public void logInPlayerStep() {
        PostLogInCreatedPlayerRequest bodyLogIn = new PostLogInCreatedPlayerRequest()
                .grantType("password")
                .username(userNamePlayer)
                .password(passBasePlayer);

        responseLogIn = userApiService.getToken(basicToken, bodyLogIn)
                .shouldHave(statusCode(200))
                .asPojo(PostLogInCreatedPlayerResponse.class);
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
        responseInfoPlayer = userApiService.getInfoPlayer(responseRegisterPlayer.getId(), token)
                .shouldHave(statusCode(200))
                .asPojo(GetInfoPlayerResponse.class);
        log.info("response body: {}", responseInfoPlayer);
    }

    @Then("Найден игрок")
    public void playerFoundStep() {
        Assertions.assertEquals(responseRegisterPlayer.getId(), responseInfoPlayer.getId());
        Assertions.assertEquals(responseRegisterPlayer.getEmail(), responseInfoPlayer.getEmail());
    }

    @When("Запросить данные другого игрока")
    public void infoOtherPlayerStep() {
        responseInfoOtherPlayer = userApiService.getInfoOtherPlayer(352531, token)
                .shouldHave(statusCode(404))
                .asPojo(GetNotFoundResponse.class);
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
