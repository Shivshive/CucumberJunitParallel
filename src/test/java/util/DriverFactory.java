package util;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();

	public DriverFactory() {
	}

	public static void initializeDriver(String browser) {
		
		WebDriver driver=null;
		
		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
//			ChromeOptions opt1 = new ChromeOptions();
//			opt1.addArguments("--disable-notifications");
			driver = new EdgeDriver();
			break;

		case "chrome":
			try {
				WebDriverManager.chromedriver().clearDriverCache().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		}

		driverThread.set(driver);
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
	}

	public static WebDriver getDriver() {
		return driverThread.get();
	}
	
	public static void removeDriver() {
		driverThread.get().close();
		driverThread.remove();
	}

}

