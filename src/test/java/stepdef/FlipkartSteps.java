package stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import util.DriverFactory;

public class FlipkartSteps {
	
	@Given("User land on flipkart homepage")
	public void user_open_flipkart() {
//		DriverFactory.initializeDriver("chrome");
		DriverFactory.getDriver().get("https://www.flipkart.com");
//		DriverFactory.getDriver().manage().window().maximize();
		System.out.println("user_open_amazon");

	}

	@Then("User verify title of flipkart")
	public void user_verify_title_of_flipkart() {
		System.out.println("user_verify_title_of_page");
		String title = DriverFactory.getDriver().getTitle();
		System.out.println(title);
//		DriverFactory.removeDriver();
	}
	
}
