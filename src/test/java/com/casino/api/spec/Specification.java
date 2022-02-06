package com.casino.api.spec;

import com.casino.dto.PostGuestRequest;
import com.casino.dto.PostLogInCreatedPlayerRequest;
import com.casino.dto.PostRegisterPlayerRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Specification {

    public static RequestSpecification reqSpecGuest(String basicToken, PostGuestRequest credentials){
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Basic  " + basicToken)
                .setContentType(ContentType.JSON)
                .setBody(credentials)
                .build();
    }

    public static ResponseSpecification resSpecGuest(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectStatusCode(200)
                .build();
    }

    public static RequestSpecification reqSpecNewPlayer(PostRegisterPlayerRequest bodyRegisterPlayer){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBody(bodyRegisterPlayer)
                .build();
    }

    public static ResponseSpecification resSpecNewPlayer(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusLine("HTTP/1.1 201 Created")
                .expectStatusCode(201)
                .build();
    }

    public static RequestSpecification reqSpecLogIn(String basicToken, PostLogInCreatedPlayerRequest bodyLogIn){
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Basic  " + basicToken)
                .setContentType(ContentType.JSON)
                .setBody(bodyLogIn)
                .build();
    }

    public static ResponseSpecification resSpecLogIn(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectStatusCode(200)
                .build();
    }

    public static RequestSpecification reqSpecInfoPlayer(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification resSpecInfoPlayer(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification resSpecInfoOtherPlayer(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusLine("HTTP/1.1 404 Not Found")
                .expectStatusCode(404)
                .build();
    }

}



