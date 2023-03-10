package com.hotelreservation.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        plugin = {
//                "pretty",
//                "html:target/default-cucumber-reports.html",
//                "json:target/json-reports/cucumber.json",
//                "junit:target/xml-report/cucumber.xml",
//                "rerun:target/failedRerun.txt",
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//        },
//        monochrome=true,
        features = "./src/test/resources/features",
        glue = {"com/hotelreservation/stepdefinitions"},
        dryRun = false,
        tags = "@reservation_tests"
)

public class Runner {
}
