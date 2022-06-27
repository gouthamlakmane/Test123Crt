package runner_file;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./src/test/resources/QuickProductionRun_flow_feature_files/QPR_TC04.feature"},
		glue = {"stepdefinition_files"},
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
		tags = "@QPR_TC04-02",
		monochrome = true,
		dryRun = false
// dryRun = true
)

public class TestRunner {

}