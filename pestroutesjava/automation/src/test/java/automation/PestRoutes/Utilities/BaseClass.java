package automation.PestRoutes.Utilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import automation.PestRoutes.Controller.Login.SignIn;
import automation.PestRoutes.Utilities.Driver.GetWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass extends AppData {

	WebDriver driver = GetWebDriver.getInstance();
	SignIn signInPage;

	// @Parameters("browser")
	@BeforeClass

	public void beforeTest() throws IOException {

		String url = getData("url",generalData);
		String userName = getData("userName", generalData);
		String password = getData("password", generalData);

//		if(browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//		}else if (browser.equalsIgnoreCase("ie")) {
//			driver = new InternetExplorerDriver();
//		}

		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--incognito");
//		DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		signInPage = new SignIn();
		signInPage.login(userName, password);

	}

	@AfterClass

	public void afterTest() {
		Reporter.flushReport();

		driver.close();

	}
}
