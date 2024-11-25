package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"featureFiles/DynamicTable.feature","featureFiles/OpenDropDown.feature" },
					glue="stepDefinitions" ,
					monochrome=true ,
					plugin = {"pretty", "html:target/cucumber-reports.html"})
public class RunnerClass {
}
