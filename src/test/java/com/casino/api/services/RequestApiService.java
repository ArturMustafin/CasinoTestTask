package com.casino.api.services;

import com.casino.api.assertions.AssertableResponse;
import com.casino.dto.PostGuestRequest;
import com.casino.dto.PostLogInCreatedPlayerRequest;
import com.casino.dto.PostRegisterPlayerRequest;
import lombok.extern.slf4j.Slf4j;

import static com.casino.api.spec.Specification.*;

@Slf4j
public class RequestApiService extends ApiService {

    public AssertableResponse getToken(String basicToken, PostGuestRequest guestBody) {
        return new AssertableResponse(setup(guestBody)
                .spec(reqSpecGuest(basicToken, guestBody))
                .post(EndPoints.TOKEN));
    }

    public AssertableResponse registerPlayer(String token, PostRegisterPlayerRequest bodyRegisterPlayer) {
        return new AssertableResponse(setup(bodyRegisterPlayer)
                .spec(reqSpecNewPlayer(bodyRegisterPlayer))
                .header("Authorization", "Bearer " + token)
                .post(EndPoints.LOG_IN));
    }

    public AssertableResponse getToken(String basicToken, PostLogInCreatedPlayerRequest bodyLogIn) {
        return new AssertableResponse(setup(bodyLogIn)
                .spec(reqSpecGuest(basicToken, bodyLogIn))
                .post(EndPoints.TOKEN));
    }

    public AssertableResponse getInfoPlayer(int idPlayer, String token) {
        return new AssertableResponse(setup(null)
                .header("Authorization", "Bearer " + token)
                .get(EndPoints.PLAYER_ID + idPlayer));
    }
}


