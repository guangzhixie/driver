package com.driver.main;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/itest/resources/cucumber"}, tags = {"~@ignore"})
public class CucumberIT {
}