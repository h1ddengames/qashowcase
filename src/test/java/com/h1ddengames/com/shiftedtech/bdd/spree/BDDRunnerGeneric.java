package com.h1ddengames.com.shiftedtech.bdd.spree;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    dryRun = true, /* Run just to check if the syntax if fine */
    //tags = { "@Acceptance" }, /* Will run tests that only has the acceptance tag */
    //tags = { "@Acceptance, @Functional"}, /* Will run any test that has either the acceptance tag OR the functional tag */
    tags = { "@Functional", "@Positive", "@Usability"}, /* Will run any test that has all the tags listed */
    //monochrome = true, /* */
    features = {"src/test/resources/features/spree/SpreeLoginFunctionalityGeneric.feature"}, /* Specify where the feature file(s) are located */
    glue = "com/h1ddengames/framework/steps/spree", /* Where are the step definitions located. Do not type /src/java/[main/test] */
    plugin = {
            "pretty:target/cucumber-test-report/generic/cucumber-pretty.txt",
            "html:target/cucumber-test-report/generic/",
            "json:target/cucumber-test-report/generic/cucumber-report.json",
            "junit:target/cucumber-test-report/generic/test-report.xml"
    }
)
public class BDDRunnerGeneric extends AbstractTestNGCucumberTests { }
