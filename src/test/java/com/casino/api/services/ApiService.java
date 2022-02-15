package com.casino.api.services;

import com.casino.ProjectConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ApiService {

    protected <T> RequestSpecification setup(T body) {
        if (body != null) {
            log.info("request body: {}", body);
        }
        return RestAssured
                .given().contentType(ContentType.JSON)
                .when()
                .filters(changeLog());
    }

    // on & off log
    private List<Filter> changeLog() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        if (config.logging()) {
            return Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());

        }
        return Collections.singletonList(new AllureRestAssured());
    }
}
