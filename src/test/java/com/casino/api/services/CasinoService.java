//package com.casino.api.services;
//
//import com.casino.dto.*;
//import com.casino.response.GetNotFoundResponse;
//import com.casino.response.RegisterPlayerResponse;
//import lombok.extern.slf4j.Slf4j;
//
//import static com.casino.api.spec.Specification.*;
//import static io.restassured.RestAssured.given;
//
//@Slf4j
//public class CasinoService {
//
//    public <T> Object getToken(String basicToken, T body, Class resultClass) {
//        return given()
//                .spec(reqSpecGuest(basicToken, body))
//                .when()
//                .post(String.valueOf(EndPoint.TOKEN))
//                .prettyPeek()
//                .then().spec(resSpec200())
//                .extract().body().as(resultClass);
//    }
//
//    public RegisterPlayerResponse registerPlayer(String token, PostRegisterPlayerRequest bodyRegisterPlayer) {
//        return given()
//                .spec(reqSpecNewPlayer(bodyRegisterPlayer))
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .post(String.valueOf(EndPoint.LOG_IN))
//                .prettyPeek()
//                .then().spec(resSpec201())
//                .extract().body().as(RegisterPlayerResponse.class);
//    }
//
//    public GetInfoPlayerResponse getInfoPlayer(int idPlayer, String token) {
//        return given().spec(reqSpecInfoPlayer())
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .get(String.valueOf(EndPoint.PLAYER_ID) + idPlayer)
//                .prettyPeek()
//                .then().spec(resSpec200())
//                .extract().body().as(GetInfoPlayerResponse.class);
//    }
//
//    public GetNotFoundResponse getInfoOtherPlayer(int idPlayer, String token) {
//        return given().spec(reqSpecInfoPlayer())
//                .header("Authorization", "Bearer " + token)
//                .when()
//                .get(String.valueOf(EndPoint.PLAYER_ID) + idPlayer)
//                .prettyPeek()
//                .then().spec(resSpecInfoOtherPlayer())
//                .extract().body().as(GetNotFoundResponse.class);
//    }
//
//
//}
