package com.h1ddengames.com.shiftedtech.spree;


import io.cucumber.testng.*;

@CucumberOptions(
    //dryRun = true, /* Run just to check if the syntax if fine */
    //tags = { "@Acceptance" }, /* Will run tests that only has the acceptance tag */
    //tags = { "@Acceptance, @Functional"}, /* Will run any test that has both the acceptance tag and the functional tag */
    //monochrome = true, /* */
    features = {"src/test/resources/features/spree"}, /* Specify where the feature files are located */
    glue = "com/h1ddengames/framework/steps/spree", /* Where are the step definitions located. Do not type /src/java/[main/test] */
    plugin = {
            "pretty:target/cucumber-test-report/cucumber-pretty.txt",
            "html:target/cucumber-test-report/",
            "json:target/cucumber-test-report/cucumber-report.json",
            "junit:target/cucumber-test-report/test-report.xml"
    }
)
public class BDDRunner extends AbstractTestNGCucumberTests { }
