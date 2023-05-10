package tests.accounts;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features"}, glue = {"model/cucumber/definitions"},
        plugin = {})
public class TestRunner extends AbstractTestNGCucumberTests {
}