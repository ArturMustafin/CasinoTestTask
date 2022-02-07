package com.casino.api;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = "@Enabled",
        glue = {"com.casino.api.steps"},
        features = "src/test/resources/api/features")
public class RunCucumberTest {
}

