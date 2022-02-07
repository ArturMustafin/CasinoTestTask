package com.casino.ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = "@Enabled",
        glue = {"com.casino.ui.steps"},
        features = "src/test/resources/ui/features")
public class RunCucumberTest {
}

