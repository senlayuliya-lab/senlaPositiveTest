package com.example.apitesting.bdd.hooks;

import com.example.apitesting.config.RestAssuredConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CucumberHooks {
    private static final Logger log = LogManager.getLogger(CucumberHooks.class);

    @Before
    public void setup(Scenario scenario) {
        log.info("START SCENARIO: {}", scenario.getName());
        log.info("Tags: {}", scenario.getSourceTagNames());

        RestAssuredConfig.setup();
        log.debug("RestAssured configured");

        Allure.addAttachment("Scenario", scenario.getName());
        Allure.addAttachment("Tags", scenario.getSourceTagNames().toString());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("SCENARIO FAILED: {}", scenario.getName());
            Allure.addAttachment("Status", "FAILED");
        } else {
            log.info("SCENARIO PASSED: {}", scenario.getName());
            Allure.addAttachment("Status", "PASSED");
        }
    }
}