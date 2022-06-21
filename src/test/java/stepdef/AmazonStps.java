package stepdef;

import io.cucumber.core.logging.Logger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import util.DriverFactory;
import static org.junit.Assert.*;

public class AmazonStps {

	@Given("User open Amazon")
	public void user_open_amazon() {
//		DriverFactory.initializeDriver("chrome");
		DriverFactory.getDriver().get("https://www.amazon.in");
//		DriverFactory.getDriver().manage().window().maximize();
		System.out.println("user_open_amazon");

	}

	@Then("User Verify Title of Page")
	public void user_verify_title_of_page() {
		System.out.println("user_verify_title_of_page");
		String title = DriverFactory.getDriver().getTitle();
		System.out.println(title);
//		fail("Intentional Fail of Amazon Test Case.. !");
	}

}
