package runners;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		//glue = "stepdef",
		extraGlue = {"hooks","stepdef"},
		stepNotifications = true
)
public class Junit_Runner {}
