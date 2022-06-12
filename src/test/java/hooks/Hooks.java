package hooks;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import util.DriverFactory;

public class Hooks {

	@Before
	public void instantiateDriver() {
		DriverFactory.initializeDriver("chrome");
	}

	@After(order = 2)
	public void takeScreenShot(Scenario scenario) {
		Shutterbug.shootPage(DriverFactory.getDriver(), Capture.VIEWPORT).save();
		System.out.println("Scenario Name : "+scenario.getName()+" || Sceanrio After Block || Shutterbut Screen Shot Taken");
	}

	@After(order = 1)
	public void terminateDriver() {
		DriverFactory.removeDriver();
	}

}
