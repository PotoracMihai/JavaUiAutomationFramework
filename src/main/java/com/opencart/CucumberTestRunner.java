package com.opencart;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/com/opencart/feature",
        glue = "com.opencart.stepdefinition",
        tags = "@run"
)

public class CucumberTestRunner {

}