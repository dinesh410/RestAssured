package com.ratest.cucumber;

import org.junit.runner.RunWith;

import com.ratest.testbase.TestBase;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/")
public class StudentRunner extends TestBase{
}
