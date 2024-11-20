package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"stepdefinition"},
		plugin = {"pretty","json:target/cucumber.json","json:target/cucumber.html"},
		tags = ""
		)
public class TestRunner {

}
