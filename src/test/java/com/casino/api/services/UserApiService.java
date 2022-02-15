package com.casino.api.services;

import com.casino.api.assertions.AssertableResponse;
import com.casino.dto.PostGuestRequest;
import com.casino.dto.PostLogInCreatedPlayerRequest;
import com.casino.dto.PostRegisterPlayerRequest;
import lombok.extern.slf4j.Slf4j;

import static com.casino.api.spec.Specification.*;

@Slf4j
public class UserApiService extends ApiService{
    public AssertableResponse getToken(String basicToken, PostGuestRequest guestBody){
        log.info("request body: {}", guestBody);
        return new AssertableResponse(setup()
                .spec(reqSpecGuest(basicToken, guestBody))
                .when()
                .post(String.valueOf(EndPoint.TOKEN)));
    }

    public AssertableResponse registerPlayer(String token, PostRegisterPlayerRequest bodyRegisterPlayer){
        log.info("request body: {}", bodyRegisterPlayer);
        return new AssertableResponse(setup()
                .spec(reqSpecNewPlayer(bodyRegisterPlayer))
                .header("Authorization", "Bearer " + token)
                .when()
                .post(String.valueOf(EndPoint.LOG_IN)));
    }

    public AssertableResponse getToken(String basicToken, PostLogInCreatedPlayerRequest bodyLogIn){
        log.info("request body: {}", bodyLogIn);
        return new AssertableResponse(setup()
                .spec(reqSpecGuest(basicToken, bodyLogIn))
                .when()
                .post(String.valueOf(EndPoint.TOKEN)));
    }

    public AssertableResponse getInfoPlayer(int idPlayer, String token) {
        return new AssertableResponse(setup()
                .spec(reqSpecInfoPlayer())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(String.valueOf(EndPoint.PLAYER_ID) + idPlayer));
    }

    public AssertableResponse getInfoOtherPlayer(int idPlayer, String token) {
        return  new AssertableResponse(setup()
                .spec(reqSpecInfoPlayer())
                .header("Authorization", "Bearer " + token)
                .when()
                .get(String.valueOf(EndPoint.PLAYER_ID) + idPlayer));
    }
}


