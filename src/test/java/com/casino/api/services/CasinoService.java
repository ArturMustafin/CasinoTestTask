package com.casino.api.services;

import com.casino.api.BaseTest;
import com.casino.dto.*;


import lombok.extern.slf4j.Slf4j;


import static com.casino.api.spec.Specification.*;
import static io.restassured.RestAssured.given;

@Slf4j
public class CasinoService extends BaseTest {
    private String BASE_URL = "http://test-api.d6.dev.devcaz.com";

    public PostGuestResponse getTokenGuest(String basicToken, PostGuestRequest credentials) {
        return given()
                .spec(reqSpecGuest(basicToken, credentials))
                .when()
                .post(BASE_URL + EndPoints.getTOKEN())
                .prettyPeek()
                .then().spec(resSpecGuest())
                .extract().body().as(PostGuestResponse.class);
    }

    public PostRegisterPlayerResponse registerPlayer(String token, PostRegisterPlayerRequest bodyRegisterPlayer) {
        return given()
                .spec(reqSpecNewPlayer(bodyRegisterPlayer))
                .header("Authorization", "Bearer " + token)
                .when()
                .post(BASE_URL + EndPoints.getLOG_IN())
                .prettyPeek()
                .then().spec(resSpecNewPlayer())
                .extract().body().as(PostRegisterPlayerResponse.class);
    }

    public PostLogInCreatedPlayerResponse logInCreatedPlayer(String basicToken, PostLogInCreatedPlayerRequest bodyLogIn) {
        return given().spec(reqSpecLogIn(basicToken, bodyLogIn))
                .when()
                .post(BASE_URL + EndPoints.getTOKEN())
                .prettyPeek()
                .then().spec(resSpecLogIn())
                .extract().body().as(PostLogInCreatedPlayerResponse.class);
    }

    public GetInfoPlayerResponse getInfoPlayer(int idPlayer, String token) {
        return given().spec(reqSpecInfoPlayer())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(BASE_URL + EndPoints.getPLAYER_ID() + idPlayer)
                .prettyPeek()
                .then().spec(resSpecInfoPlayer())
                .extract().body().as(GetInfoPlayerResponse.class);
    }

    public GetNotFoundResponse getInfoOtherPlayer(int idPlayer, String token) {
        return given().spec(reqSpecInfoPlayer())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(BASE_URL + EndPoints.getPLAYER_ID() + idPlayer)
                .prettyPeek()
                .then().spec(resSpecInfoOtherPlayer())
                .extract().body().as(GetNotFoundResponse.class);
    }
}
