package com.casino.api;


import io.cucumber.java.Before;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Slf4j
public class BaseTest {
    public static Properties prop = new Properties();

    @Before
    protected static void before() {
        //for logging request and responses in Allure reporting
        RestAssured.filters(new AllureRestAssured());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        loadProperties();
        RestAssured.baseURI = prop.getProperty("base.url");
    }

    public static void loadProperties() {
        try (InputStream file = new FileInputStream("src/test/resources/application.properties")) {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
