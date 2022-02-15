package com.casino.api.spec;

import com.casino.dto.PostRegisterPlayerRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Specification {

    public static <T> RequestSpecification reqSpecGuest(String basicToken, T body) {
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Basic " + basicToken)
                .setBody(body)
                .build();
    }

    public static RequestSpecification reqSpecNewPlayer(PostRegisterPlayerRequest bodyRegisterPlayer) {
        return new RequestSpecBuilder()
                .setBody(bodyRegisterPlayer)
                .build();
    }
}