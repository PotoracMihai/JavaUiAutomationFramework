package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void beforeAll() {

        logger.log(Level.INFO, "The execution of the test suite started");
    }

    @Before
    public void executeBeforeEachTest() {

        logger.log(Level.INFO, "The test is started");
    }

    @After
    public void executeAfterEachTest() {

        logger.log(Level.INFO, "The test is completed");
        DriverManager.getInstance().quiteDriver();
    }

    @AfterAll
    public static void afterAll() {

        logger.log(Level.INFO, "The test execution finished");
    }
}
